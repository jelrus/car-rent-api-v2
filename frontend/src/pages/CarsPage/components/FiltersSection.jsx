import PropTypes from 'prop-types';
import './FiltersSection.css';
import { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import InputField from '@/components/atoms/InputField/InputField.jsx';
import SelectField from '@components/atoms/SelectField/SelectField.jsx';
import Button from '@components/atoms/Button/Button.jsx';
import PriceRange from './PriceRange.jsx';

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
}) => {
  const dispatch = useDispatch();

  const [localFilters, setLocalFilters] = useState({
    pickupLocationId: '',
    dropOffLocationId: '',
    pickupDate: '',
    dropOffDate: '',
    category: '',
    gearBoxType: '',
    fuelType: '',
    priceRange: [minPrice, maxPrice],
  });

  useEffect(() => {
    setLocalFilters((prevFilters) => ({
      ...prevFilters,
      priceRange: [minPrice, maxPrice],
    }));
  }, [minPrice, maxPrice]);

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
    dispatch(onApplyFilters(localFilters));
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
          <InputField
            label="Pick-up date"
            type="datetime-local"
            id="pickupDate"
            name="pickupDate"
            value={localFilters.pickupDate}
            onChange={handleInputChange}
            placeholder="Select pick-up date"
          />
          <InputField
            label="Drop-off date"
            type="datetime-local"
            id="dropOffDate"
            name="dropOffDate"
            value={localFilters.dropOffDate}
            onChange={handleInputChange}
            placeholder="Select drop-off date"
          />
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
            <Button type="primary" text="Find a car" />
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
  minPrice: PropTypes.number.isRequired,
  maxPrice: PropTypes.number.isRequired,
};

export default FiltersSection;
