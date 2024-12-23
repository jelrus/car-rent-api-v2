import CarCard from '../../components/molecules/CarCard/CarCard.jsx';

import './CarsPage.css';
import FiltersSection from "@pages/CarsPage/components/FiltersSection.jsx";

import { useEffect, useState } from 'react';

const fetchData = async () => {
    try {
        const response = await fetch('/cars.json');
        if (!response.ok) {
            throw new Error('Failed to fetch data from JSON');
        }
        const carsData = await response.json();
        return carsData;
    } catch (error) {
        console.error('Error fetching data:', error);
        return [];
    }
};

const getUniqueValues = (data, key) => {
    const values = data.map((item) => item[key]);
    return [...new Set(values)];
};

const CarsPage = () => {

    const [carsData, setCarsData] = useState([]);
    // const [cities, setCities] = useState([]);
    const [pickupLocations, setPickupLocations] = useState([]);
    const [dropOffLocations, setDropOffLocations] = useState([]);
    const [gearBoxies, setGearBoxies] = useState([]);
    const [categories, setCategories] = useState([]);
    const [fuelTypes, setFuelTypes] = useState([]);
    const [filteredCarsData, setFilteredCarsData] = useState([]);

    useEffect(() => {
        const fetchDataAndProcess = async () => {
            const data = await fetchData();
            setCarsData(data);
            setFilteredCarsData(data);
            // setCities(getUniqueValues(data, 'location'));
            setPickupLocations(getUniqueValues(data, 'pickupLocationId'));
            setDropOffLocations(getUniqueValues(data, 'dropOffLocationId'));
            setGearBoxies(getUniqueValues(data, 'gearBoxType'));
            setCategories(getUniqueValues(data, 'category'));
            setFuelTypes(getUniqueValues(data, 'fuelType'));
        }
        fetchDataAndProcess();
    }, []);

    const handleApplyFilters = (filters) => {
        console.log('Filters applied:', filters);
        const filtered = carsData.filter((car) => {
            const isPickupMatch = filters.pickupLocation ? car.pickupLocationId === filters.pickupLocation : true;
            const isDropOffMatch = filters.dropOffLocation ? car.dropOffLocationId === filters.dropOffLocation : true;
            const isCategoryMatch = filters.category ? car.category === filters.category : true;
            const isGearBoxMatch = filters.gearBoxType ? car.gearBoxType === filters.gearBoxType : true;
            const isFuelTypeMatch = filters.typeOfEngine ? car.fuelType === filters.typeOfEngine : true;
            const isPriceMatch = car.pricePerDay >= filters.priceRange[0] && car.pricePerDay <= filters.priceRange[1];

            return isPickupMatch && isDropOffMatch && isCategoryMatch && isGearBoxMatch && isFuelTypeMatch && isPriceMatch;
        });

        setFilteredCarsData(filtered);
    };

    return (
        <div className='cars-page'>

            <FiltersSection
                title='Choose a car for rental'
                pickupLocations={pickupLocations}
                dropOffLocations={dropOffLocations}
                categories={categories}
                gearBoxies={gearBoxies}
                fuelTypes={fuelTypes}
                onApplyFilters={handleApplyFilters}
            />

            <div className="cars-page__car-list">
                {filteredCarsData.map((car) => (
                    <CarCard key={car.carId} car={car}/>
                ))}
            </div>
        </div>
    )
}
export default CarsPage;