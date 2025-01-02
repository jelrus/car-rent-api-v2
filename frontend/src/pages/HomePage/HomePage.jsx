import { useSelector } from 'react-redux';
import FiltersSection from '../CarsPage/components/FiltersSection/FiltersSection.jsx';

const HomePage = () => {
  const {
    pickupLocations,
    dropOffLocations,
    categories,
    gearBoxies,
    fuelTypes,
    minPrice,
    maxPrice,
    onApplyFilters,
  } = useSelector((state) => state.cars);
  return (
    <div>
      <FiltersSection
        title="Choose a car for rental"
        pickupLocations={pickupLocations}
        dropOffLocations={dropOffLocations}
        categories={categories}
        gearBoxies={gearBoxies}
        fuelTypes={fuelTypes}
        minPrice={minPrice}
        maxPrice={maxPrice}
        onApplyFilters={onApplyFilters}
      />
    </div>
  );
};

export default HomePage;
