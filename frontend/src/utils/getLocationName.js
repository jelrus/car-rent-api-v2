import locations from '../data/locations';
const getLocationName = (locationId) => {
  const location = locations.find((loc) => loc.locationId == locationId);
  return location ? location.locationName : 'Unknown Location';
};

export default getLocationName;
