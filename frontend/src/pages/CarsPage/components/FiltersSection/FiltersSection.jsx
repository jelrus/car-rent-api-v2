import PropTypes from 'prop-types';
import './FiltersSection.css';
import { useState, useEffect, useRef } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';
import SelectField from '@components/atoms/SelectField/SelectField.jsx';
import Button from '@components/atoms/Button/Button.jsx';
import PriceRange from './PriceRange.jsx';
import CustomCalendar from '../CustomCalendar/CustomCalendar.jsx';
import clearIcon from '@assets/clear.svg';

const FiltersSection = ({
  title,
  pickupLocations,
  dropOffLocations,
  categories,
  gearBoxies,
  fuelTypes,
  minPrice,
  maxPrice,
  onApplyFilters,
  onClearFilters,
  bookedDays,
}) => {
  const [searchParams, setSearchParams] = useSearchParams();
  const calendarRef = useRef(null);
  const navigate = useNavigate();
  const [localFilters, setLocalFilters] = useState({
    pickupLocationId: '',
    dropOffLocationId: '',
    pickupDate: null,
    dropOffDate: null,
    pickupTime: '',
    dropOffTime: '',
    category: '',
    gearBoxType: '',
    fuelType: '',
    priceRange: [minPrice, maxPrice],
  });

  const [isCalendarVisible, setIsCalendarVisible] = useState(false);
  const [activeField, setActiveField] = useState(null);

  useEffect(() => {
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      priceRange: [minPrice, maxPrice],
    }));
  }, [minPrice, maxPrice]);

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
      [`${field}Date`]: date,
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

  const handlePriceChange = (newRange) => {
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      priceRange: newRange,
    }));
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const params = new URLSearchParams();
    Object.entries(localFilters).forEach(([key, value]) => {
      if (value) {
        if (Array.isArray(value)) {
          params.append(key, value.join(','));
        } else if (value instanceof Date) {
          params.append(key, value.toISOString());
        } else {
          params.append(key, value);
        }
      }
    });

    setSearchParams(params);
    navigate(`/cars?${params.toString()}`);
    onApplyFilters(localFilters);
  };

  return (
    <div className="filters-section">
      <h2 className="filters-section__title">{title}</h2>
      <form className="filters-form" onSubmit={handleSubmit}>
        <div className="filters-form__row">
          <SelectField
            className="filters-form__select-location"
            label="Pick-up location"
            id="pickupLocationId"
            name="pickupLocationId"
            value={localFilters.pickupLocationId}
            onChange={handleInputChange}
            options={pickupLocations}
          />
          <SelectField
            className="filters-form__select-location"
            label="Drop-off location"
            id="dropOffLocationId"
            name="dropOffLocationId"
            value={localFilters.dropOffLocationId}
            onChange={handleInputChange}
            options={dropOffLocations}
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
                {localFilters.pickupDate
                  ? `${localFilters.pickupDate.toLocaleDateString('en-US', {
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
                {localFilters.dropOffDate
                  ? `${localFilters.dropOffDate.toLocaleDateString('en-US', {
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
                  bookedDays={bookedDays}
                  selectedDates={{
                    pickup: {
                      date: localFilters.pickupDate,
                      time: localFilters.pickupTime || '07:00AM',
                    },
                    dropOff: {
                      date: localFilters.dropOffDate,
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
            value={localFilters.category}
            onChange={handleInputChange}
            options={categories}
          />
          <SelectField
            className="filters-form__select-infoOfCar"
            id="gearBoxType"
            label="Gearbox"
            name="gearBoxType"
            value={localFilters.gearBoxType}
            onChange={handleInputChange}
            options={gearBoxies}
          />
          <SelectField
            className="filters-form__select-infoOfCar"
            id="fuelType"
            label="Type of engine"
            name="fuelType"
            value={localFilters.fuelType}
            onChange={handleInputChange}
            options={fuelTypes}
          />
          <div className="filters-form__input-range">
            <div className="filters-form__input-range_label">
              <span className="filters-form__input-range_label-price">
                Price per day
              </span>
              <span>
                ${localFilters.priceRange[0]} - ${localFilters.priceRange[1]}
              </span>
            </div>
            <PriceRange
              min={minPrice}
              max={maxPrice}
              step={5}
              value={{
                min: localFilters.priceRange[0],
                max: localFilters.priceRange[1],
              }}
              onChange={(newValue) =>
                handlePriceChange([newValue.min, newValue.max])
              }
            />
          </div>
          <div className="filters-form__button">
            <Button type="submit" ButtonType="primary" text="Find a car" />
            <button
              type="button"
              className="filters-form__clear-filters"
              onClick={onClearFilters}
            >
              <img src={clearIcon} alt="Clear Filters Icon" />
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

FiltersSection.propTypes = {
  pickupLocations: PropTypes.arrayOf(PropTypes.string).isRequired,
  dropOffLocations: PropTypes.arrayOf(PropTypes.string).isRequired,
  categories: PropTypes.arrayOf(PropTypes.string).isRequired,
  title: PropTypes.string,
  gearBoxies: PropTypes.arrayOf(PropTypes.string).isRequired,
  fuelTypes: PropTypes.arrayOf(PropTypes.string).isRequired,
  onApplyFilters: PropTypes.func.isRequired,
  onClearFilters: PropTypes.func.isRequired,
  minPrice: PropTypes.number.isRequired,
  maxPrice: PropTypes.number.isRequired,
  bookedDays: PropTypes.arrayOf(PropTypes.string),
};

export default FiltersSection;
