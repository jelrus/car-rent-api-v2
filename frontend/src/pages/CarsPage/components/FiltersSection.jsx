import PropTypes from 'prop-types';
import './FiltersSection.css';
import { useState } from 'react';
import InputField from '@/components/atoms/InputField/InputField.jsx';
import SelectField from "@components/atoms/SelectField/SelectField.jsx";
import Button from '../../../components/atoms/Button/Button.jsx';

import PriceRange from './PriceRange.jsx';
const FiltersSection = ({ title, pickupLocations, dropOffLocations, categories, gearBoxies, fuelTypes, onApplyFilters }) => {

    const [filters, setFilters] = useState({
        pickupLocation: '',
        dropOffLocation: '',
        pickupDate: '',
        dropOffDate: '',
        category: '',
        gearbox: '',
        fuelType: '',
        priceRange: [50, 700]
    });

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setFilters((prevFilters) => ({
            ...filters,
            [name]: value
        }));
    };

    const handlePriceChange = (newRange) => {
        setFilters((prevFilters) => ({
            ...prevFilters,
            priceRange: newRange
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        onApplyFilters(filters);
    };

    // const [value, setValue] = useState({ min: 0, max: 700 });

    return (
        <div className='filters-section'>
            <h2 className='filters-section__title'>{title}</h2>
            <form className='filters-form' onSubmit={handleSubmit}>
                <div className='filters-form__row'>
                        <SelectField className='filters-form__select-location'
                            label='Pick-up location'
                            id='pickupLocation'
                            name='pickupLocation'
                            value={filters.pickupLocation}
                            onChange={handleInputChange}
                            options={pickupLocations}
                        />
                        <SelectField className='filters-form__select-location'
                            label='Drop-off location'
                            id='dropOffLocation'
                            name='dropOffLocation'
                            value={filters.dropOffLocation}
                            onChange={handleInputChange}
                            options={dropOffLocations}
                        />
                        <InputField
                            label='Pick-up date'
                            type='datetime-local'
                            id='pickupDate'
                            name='pickupDate'
                            value={filters.pickupDate}
                            onChange={handleInputChange}
                        />
                        <InputField
                            label='Drop-off date'
                            type='datetime-local'
                            id='dropOffDate'
                            name='dropOffDate'
                            value={filters.dropOffDate}
                            onChange={handleInputChange}
                        />
                </div>
                <div className='filters-form__row'>
                    <SelectField className='filters-form__select-infoOfCar'
                                 id="category"
                                 label="Car category"
                                 name="category"
                                 value={filters.category}
                                 onChange={handleInputChange}
                                 options={categories}
                    />
                    <SelectField className='filters-form__select-infoOfCar'
                                 id="gearBoxType"
                                 label="Gearbox"
                                 name="gearBoxType"
                                 value={filters.gearBoxType}
                                 onChange={handleInputChange}
                                 options={gearBoxies}
                    />
                    <SelectField className='filters-form__select-infoOfCar'
                                 id="fuelType"
                                 label="Type of engine"
                                 name="typeOfEngine"
                                 value={filters.typeOfEngine}
                                 onChange={handleInputChange}
                                 options={fuelTypes}
                    />

                    <div className='filters-form__input-range'>
                        <div className="filters-form__input-range_label">
                            <span className='filters-form__input-range_label-price'>Price per day</span>
                            {/*<span> ${value.min} - ${value.max} </span>*/}
                            <span>${filters.priceRange[0]} - ${filters.priceRange[1]}</span>
                        </div>
                        <PriceRange
                            min={50}
                            max={550}
                            step={5}
                            value={{ min: filters.priceRange[0], max: filters.priceRange[1] }}
                            onChange={(newValue) => handlePriceChange([newValue.min, newValue.max])}
                        />

                    </div>

                    <div className='filters-form__button'>
                        <Button type="primary" text='Find a car'/>
                    </div>
                </div>
            </form>
        </div>
    );
};

FiltersSection.propTypes = {
    // cities: PropTypes.arrayOf(
    //     PropTypes.shape({
    //         id: PropTypes.string.isRequired,
    //         name: PropTypes.string.isRequired
    //     })
    // ).isRequired,
    pickupLocations: PropTypes.arrayOf(PropTypes.string).isRequired,
    dropOffLocations: PropTypes.arrayOf(PropTypes.string).isRequired,
    categories: PropTypes.arrayOf(PropTypes.string).isRequired,
    onApplyFilters: PropTypes.func.isRequired,
    title: PropTypes.string,
    gearBoxies: PropTypes.arrayOf(PropTypes.string).isRequired,
    fuelTypes: PropTypes.arrayOf(PropTypes.string).isRequired,
};

export default FiltersSection;