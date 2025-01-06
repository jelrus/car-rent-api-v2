import './FiltersSection.css';
import { useState, useEffect, useRef } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { fetchCars, setFilters, clearFilters } from '@/redux/slices/carsSlice';
import SelectField from '@components/atoms/SelectField/SelectField.jsx';
import Button from '@components/atoms/Button/Button.jsx';
import PriceRange from './PriceRange.jsx';
import CustomCalendar from '../CustomCalendar/CustomCalendar.jsx';
import { CATEGORIES, GEAR_BOX_TYPE, FUEL_TYPE } from '@/constants/filter';
import LOCATIONS from '@/constants/locations';

const FiltersSection = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const calendarRef = useRef(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [localFilters, setLocalFilters] = useState({
    pickupLocationId: null,
    dropOffLocationId: null,
    pickupDateTime: null,
    dropOffDateTime: null,
    category: null,
    gearBoxType: null,
    fuelType: null,
    minPrice: 0,
    maxPrice: 1000,
  });

  const [selectedDates, setSelectedDates] = useState({
    pickup: { date: null, time: '07:00AM' },
    dropOff: { date: null, time: '10:00AM' },
  });

  const [isCalendarVisible, setIsCalendarVisible] = useState(false);
  const [activeField, setActiveField] = useState(null);

  const toggleCalendar = (field) => {
    if (activeField === field && isCalendarVisible) {
      setIsCalendarVisible(false);
      setActiveField(null);
    } else {
      setActiveField(field);
      setIsCalendarVisible(true);
    }
  };

  useEffect(() => {
    const handleOutsideClick = (event) => {
      if (calendarRef.current && !calendarRef.current.contains(event.target)) {
        setIsCalendarVisible(false);
        setActiveField(null);
      }
    };

    document.addEventListener('mousedown', handleOutsideClick);
    return () => {
      document.removeEventListener('mousedown', handleOutsideClick);
    };
  }, []);

  const formatDateTime = (date) => {
    if (!date) return null;

    let dateObj;

    // Перевірка чи є об'єкт дійсним типом Date або рядком, який можна перетворити в Date
    if (
      Object.prototype.toString.call(date) === '[object Date]' &&
      !isNaN(date.getTime())
    ) {
      dateObj = date; // Якщо це вже об'єкт Date
    } else if (typeof date === 'string') {
      dateObj = new Date(date); // Якщо це рядок, намагаємося створити Date
    } else {
      console.error('Invalid date:', date);
      return null;
    }

    // Перевірка на валідність Date
    if (isNaN(dateObj.getTime())) {
      console.error('Invalid date object:', dateObj);
      return null;
    }

    // Форматуємо в локальному часі
    const year = dateObj.getFullYear();
    const month = String(dateObj.getMonth() + 1).padStart(2, '0');
    const day = String(dateObj.getDate()).padStart(2, '0');
    const hours = String(dateObj.getHours()).padStart(2, '0');
    const minutes = String(dateObj.getMinutes()).padStart(2, '0');
    const seconds = String(dateObj.getSeconds()).padStart(2, '0');

    // Повертаємо відформатовану дату в форматі ISO (без мілісекунд)
    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
  };

  const handleDateSelect = (field, date) => {
    const time = selectedDates[field]?.time || '07:00AM';

    // Перетворюємо час у 24-годинний формат
    const [hours, minutes] = time.match(/(\d+):(\d+)(AM|PM)/).slice(1, 3);
    const isPM = time.includes('PM');
    const hours24 = isPM
      ? (parseInt(hours, 10) % 12) + 12
      : parseInt(hours, 10) % 12; // Якщо PM, додаємо 12 годин

    // Форматуємо час у формат 'HH:mm:ss'
    const formattedTime = `${String(hours24).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:00`;

    // Перетворюємо обрану дату на ISO рядок
    const newDateTime = new Date(date);

    // Коригуємо дату в залежності від локального часового поясу
    const offset = newDateTime.getTimezoneOffset(); // Відстань в хвилинах від UTC
    newDateTime.setMinutes(newDateTime.getMinutes() - offset); // Виправляємо відставання

    // Форматуємо дату та час
    const formattedDateTime = `${newDateTime.toISOString().split('T')[0]}T${formattedTime}`;

    // Оновлюємо localFilters з новим значенням для pickupDateTime або dropOffDateTime
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      [`${field}DateTime`]: formattedDateTime,
    }));
  };

  const handleTimeSelect = (field, time) => {
    setSelectedDates((prev) => ({
      ...prev,
      [field]: { ...prev[field], time },
    }));

    // Беремо поточну дату для обраного поля, або використовуємо поточну дату, якщо її немає
    const date = selectedDates[field]?.date || new Date();
    const newDateTime = new Date(date);

    // Перетворюємо час у 24-годинний формат
    const [hours, minutes] = time.match(/(\d+):(\d+)(AM|PM)/).slice(1, 3);
    const isPM = time.includes('PM');
    const hours24 = isPM
      ? (parseInt(hours, 10) % 12) + 12
      : parseInt(hours, 10) % 12; // Якщо PM, додаємо 12 годин

    // Форматуємо час у формат 'HH:mm:ss'
    const formattedTime = `${String(hours24).padStart(2, '0')}:${String(minutes).padStart(2, '0')}:00`;

    // Коригуємо дату в залежності від локального часового поясу
    const offset = newDateTime.getTimezoneOffset(); // Відстань в хвилинах від UTC
    newDateTime.setMinutes(newDateTime.getMinutes() - offset); // Виправляємо відставання

    // Форматуємо дату та час
    const formattedDateTime = `${newDateTime.toISOString().split('T')[0]}T${formattedTime}`;

    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      [`${field}DateTime`]: formattedDateTime,
    }));
  };

  const handleInputChange = (event) => {
    const { name, value } = event.target;

    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value,
    }));
  };

  const handleApplyFilters = (newFilters) => {
    // Форматуємо дати перед застосуванням фільтрів
    const formattedFilters = {
      ...newFilters,
      pickupDateTime: newFilters.pickupDateTime
        ? formatDateTime(newFilters.pickupDateTime) // Виклик функції форматування для pickupDateTime
        : null,
      dropOffDateTime: newFilters.dropOffDateTime
        ? formatDateTime(newFilters.dropOffDateTime) // Виклик функції форматування для dropOffDateTime
        : null,
    };

    console.log('Formatted Filters', formattedFilters);

    // Застосовуємо відформатовані фільтри
    dispatch(setFilters(formattedFilters));
    dispatch(fetchCars(formattedFilters));
  };

  const handleClearFilters = () => {
    dispatch(clearFilters());
    setSearchParams({});
    setLocalFilters({
      pickupLocationId: null,
      dropOffLocationId: null,
      pickupDateTime: null,
      dropOffDateTime: null,
      pickupTime: null,
      dropOffTime: null,
      category: null,
      gearBoxType: null,
      fuelType: null,
      minPrice: 0,
      maxPrice: 1000,
    });
    dispatch(fetchCars({}));
  };

  const handlePriceChange = (newMinPrice, newMaxPrice) => {
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      minPrice: newMinPrice,
      maxPrice: newMaxPrice,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const formatDateTime = (date) => {
      if (!date) return null;

      // Створюємо нову дату, щоб уникнути змін оригіналу
      const localDate = new Date(date);

      // Коригуємо дату в залежності від локального часового поясу
      const offset = localDate.getTimezoneOffset(); // Відстань в хвилинах від UTC
      localDate.setMinutes(localDate.getMinutes() - offset); // Виправляємо відставання

      // Форматуємо дату в форматі 'yyyy-MM-ddTHH:mm:ss'
      const formattedDate = localDate
        .toISOString() // Отримуємо ISO строку, що містить дату і час в UTC
        .split('.')[0]; // Видаляємо мілісекунди

      return formattedDate;
    };

    Object.keys(localFilters).forEach((key) => {
      if (localFilters[key] === '') {
        localFilters[key] = null;
      }
    });

    console.log('Filters before applying:', localFilters);

    const params = new URLSearchParams();
    Object.entries(localFilters).forEach(([key, value]) => {
      if (value) {
        if (Array.isArray(value)) {
          params.append(key, value.join(','));
        } else if (value instanceof Date) {
          params.append(key, formatDateTime(value)); // Форматуємо дату і час тут
        } else {
          params.append(key, value);
        }
      }
    });

    const paramsString = params.toString().replace(/%3A/g, ':');
    setSearchParams(paramsString);
    navigate(`/cars?${paramsString}`);
    handleApplyFilters(localFilters);
  };

  return (
    <div className="filters-section">
      <h2 className="filters-section__title">Choose a car for rental </h2>
      <form className="filters-form" onSubmit={handleSubmit}>
        <div className="filters-form__clear-filters-block">
          <button
            type="button"
            className="filters-form__clear-filters"
            onClick={handleClearFilters}
          >
            Clear all filters
          </button>
        </div>
        <div className="filters-form__row">
          <SelectField
            className="filters-form__select-location"
            label="Pick-up location"
            id="pickupLocationId"
            name="pickupLocationId"
            value={localFilters.pickupLocationId || ''}
            onChange={handleInputChange}
            options={LOCATIONS}
          />
          <SelectField
            className="filters-form__select-location"
            label="Drop-off location"
            id="dropOffLocationId"
            name="dropOffLocationId"
            value={localFilters.dropOffLocationId || ''}
            onChange={handleInputChange}
            options={LOCATIONS}
          />
          <div className="filters-form__date-picker-fields" ref={calendarRef}>
            <div>
              <p className="filters-form__label-date">Pick-up date</p>
              <div
                className={`filters-form__date-picker-field ${
                  activeField === 'pickup' ? 'active' : ''
                }`}
                onClick={() => toggleCalendar('pickup')}
              >
                {localFilters.pickupDateTime
                  ? `${localFilters.pickupDateTime.split('T')[0]} ${localFilters.pickupTime || '07:00AM'}`
                  : 'Pick-up date'}
                <span className="filters-form__dropdown-arrow">&#9662;</span>
              </div>
            </div>
            <div>
              <p className="filters-form__label-date">Drop-off date</p>
              <div
                className={`filters-form__date-picker-field ${
                  activeField === 'dropOff' ? 'active' : ''
                }`}
                onClick={() => toggleCalendar('dropOff')}
              >
                {localFilters.dropOffDateTime
                  ? `${localFilters.dropOffDateTime.split('T')[0]} ${localFilters.dropOffTime || '10:00AM'}`
                  : 'Drop-off date'}
                <span className="filters-form__dropdown-arrow">&#9662;</span>
              </div>
            </div>
            {isCalendarVisible && (
              <div className="filters-form__calendar-wrapper">
                <CustomCalendar
                  bookedDays={[]}
                  selectedDates={selectedDates}
                  onDateSelect={(field, date) => handleDateSelect(field, date)}
                  onTimeSelect={(field, time) => handleTimeSelect(field, time)}
                />
              </div>
            )}
          </div>
        </div>
        <div className="filters-form__row">
          <SelectField
            className="filters-form__select-infoOfCar"
            id="category"
            label="Car category"
            name="category"
            value={localFilters.category || ''}
            onChange={handleInputChange}
            options={CATEGORIES}
          />
          <SelectField
            className="filters-form__select-infoOfCar"
            id="gearBoxType"
            label="Gearbox"
            name="gearBoxType"
            value={localFilters.gearBoxType || ''}
            onChange={handleInputChange}
            options={GEAR_BOX_TYPE}
          />
          <SelectField
            className="filters-form__select-infoOfCar"
            id="fuelType"
            label="Type of engine"
            name="fuelType"
            value={localFilters.fuelType || ''}
            onChange={handleInputChange}
            options={FUEL_TYPE}
          />
          <div className="filters-form__input-range">
            <div className="filters-form__input-range_label">
              <span className="filters-form__input-range_label-price">
                Price per day
              </span>
              <span>
                ${localFilters.minPrice} - ${localFilters.maxPrice}
              </span>
            </div>
            <PriceRange
              min={0}
              max={1000}
              step={5}
              value={{
                min: localFilters.minPrice,
                max: localFilters.maxPrice,
              }}
              onChange={(newValue) =>
                handlePriceChange(newValue.min, newValue.max)
              }
            />
          </div>
          <div className="filters-form__button">
            <Button type="submit" ButtonType="primary" text="Find a car" />
          </div>
        </div>
      </form>
    </div>
  );
};

export default FiltersSection;
