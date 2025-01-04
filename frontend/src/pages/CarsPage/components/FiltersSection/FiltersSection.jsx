import PropTypes from 'prop-types';
import './FiltersSection.css';
import { useState, useEffect, useRef } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { fetchCars, setFilters, clearFilters } from '@/redux/slices/carsSlice';
import SelectField from '@components/atoms/SelectField/SelectField.jsx';
import Button from '@components/atoms/Button/Button.jsx';
import PriceRange from './PriceRange.jsx';
import CustomCalendar from '../CustomCalendar/CustomCalendar.jsx';

const data = {
  pickupLocations: [
    {
      id: 1,
      value: 'ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4',
      name: 'Location 1',
    },
    {
      id: 2,
      value: '26979fed-8e9a-429f-810f-2fce633ad01e',
      name: 'Location 2',
    },
    {
      id: 3,
      value: '2aacd5df-b679-49f4-9c20-ecbead6e6ff9',
      name: 'Location 3',
    },
    {
      id: 4,
      value: '4f4b5e1d-841f-4006-b29c-5b0e8724ad74',
      name: 'Location 4',
    },
    {
      id: 5,
      value: '28b2926a-7d91-45ec-9957-4c910d30dced',
      name: 'Location 5',
    },
    {
      id: 6,
      value: 'f5445579-8c1d-4962-8b55-f27d49922da9',
      name: 'Location 6',
    },
    {
      id: 7,
      value: '2c9b6f42-a3f3-4508-9e1a-d3753cf36292',
      name: 'Location 7',
    },
  ],
  dropOffLocations: [
    {
      id: 1,
      value: 'ac1a3a1d-3fb2-4eb6-b27a-1c034929aee4',
      name: 'Location 1',
    },
    {
      id: 2,
      value: '26979fed-8e9a-429f-810f-2fce633ad01e',
      name: 'Location 2',
    },
    {
      id: 3,
      value: '2aacd5df-b679-49f4-9c20-ecbead6e6ff9',
      name: 'Location 3',
    },
    {
      id: 4,
      value: '4f4b5e1d-841f-4006-b29c-5b0e8724ad74',
      name: 'Location 4',
    },
    {
      id: 5,
      value: '28b2926a-7d91-45ec-9957-4c910d30dced',
      name: 'Location 5',
    },
    {
      id: 6,
      value: 'f5445579-8c1d-4962-8b55-f27d49922da9',
      name: 'Location 6',
    },
    {
      id: 7,
      value: '2c9b6f42-a3f3-4508-9e1a-d3753cf36292',
      name: 'Location 7',
    },
  ],
  categories: [
    { id: 8, value: 'ECONOMY', name: 'Economy' },
    { id: 9, value: 'COMFORT', name: 'Comfort' },
    { id: 10, value: 'BUSINESS', name: 'Business' },
    { id: 11, value: 'PREMIUM', name: 'Premium' },
    { id: 12, value: 'CROSSOVER', name: 'Crossover' },
    { id: 13, value: 'MINIVAN', name: 'Minivan' },
    { id: 14, value: 'ELECTRIC', name: 'Electric' },
  ],
  gearBoxType: [
    { id: 15, value: 'MANUAL', name: 'Manual' },
    { id: 16, value: 'AUTOMATIC', name: 'Automatic' },
  ],
  fuelType: [
    { id: 17, value: 'PETROL', name: 'Petrol' },
    { id: 18, value: 'DIESEL', name: 'Diesel' },
    { id: 19, value: 'ELECTRIC', name: 'Electric' },
    { id: 20, value: 'HYBRID', name: 'Hybrid' },
  ],
};

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
    pickupTime: null,
    dropOffTime: null,
    category: null,
    gearBoxType: null,
    fuelType: null,
    minPrice: 0,
    maxPrice: 1000,
  });

  const [isCalendarVisible, setIsCalendarVisible] = useState(false);
  const [activeField, setActiveField] = useState(null);

  // useEffect(() => {
  //   setLocalFilters((prevFilters) => ({
  //     ...prevFilters,
  //     minPrice,
  //     maxPrice,
  //   }));
  // }, [minPrice, maxPrice]);

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

  const handleDateSelect = (field, date) => {
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      [`${field}DateTime`]: date,
    }));
  };

  const handleTimeSelect = (field, time) => {
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      [`${field}Time`]: time,
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
    const formatDateTime = (date) => {
      if (!date) return null;
      return date.toISOString().split('.')[0];
    };

    const formattedFilters = {
      ...newFilters,
      pickupDateTime: newFilters.pickupDateTime
        ? formatDateTime(newFilters.pickupDateTime)
        : null,
      dropOffDateTime: newFilters.dropOffDateTime
        ? formatDateTime(newFilters.dropOffDateTime)
        : null,
    };

    console.log('Formatted Filters', formattedFilters);

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
      return date.toISOString().split('.')[0];
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
          params.append(key, formatDateTime(value));
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
            options={data.pickupLocations}
          />
          <SelectField
            className="filters-form__select-location"
            label="Drop-off location"
            id="dropOffLocationId"
            name="dropOffLocationId"
            value={localFilters.dropOffLocationId || ''}
            onChange={handleInputChange}
            options={data.dropOffLocations}
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
                  ? `${localFilters.pickupDateTime.toLocaleDateString('en-US', {
                      month: 'short',
                      day: 'numeric',
                    })} ${localFilters.pickupTime || '07:00AM'}`
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
                  ? `${localFilters.dropOffDateTime.toLocaleDateString('en-US', {
                        month: 'short',
                        day: 'numeric',
                      })} ${localFilters.dropOffTime || '10:00AM'}`
                  : 'Drop-off date'}
                <span className="filters-form__dropdown-arrow">&#9662;</span>
              </div>
            </div>
            {isCalendarVisible && (
              <div className="filters-form__calendar-wrapper">
                <CustomCalendar
                  bookedDays={[]}
                  selectedDates={{
                    pickup: {
                      date: localFilters.pickupDateTime,
                      time: localFilters.pickupTime || '07:00AM',
                    },
                    dropOff: {
                      date: localFilters.dropOffDateTime,
                      time: localFilters.dropOffTime || '10:00AM',
                    },
                  }}
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
            options={data.categories}
          />
          <SelectField
            className="filters-form__select-infoOfCar"
            id="gearBoxType"
            label="Gearbox"
            name="gearBoxType"
            value={localFilters.gearBoxType || ''}
            onChange={handleInputChange}
            options={data.gearBoxType}
          />
          <SelectField
            className="filters-form__select-infoOfCar"
            id="fuelType"
            label="Type of engine"
            name="fuelType"
            value={localFilters.fuelType || ''}
            onChange={handleInputChange}
            options={data.fuelType}
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
