import LOCATIONS from '../constants/locations';
const getLocationName = (locationId) => {
  const location = LOCATIONS.find((loc) => loc.id == locationId);
  return location ? location.label : 'Unknown Location';
};

export default getLocationName;
