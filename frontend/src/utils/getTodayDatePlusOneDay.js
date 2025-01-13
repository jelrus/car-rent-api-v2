const getTodayDatePlusOneDay = () => {
  const now = new Date();
  now.setDate(now.getDate() + 1);

  const options = {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: false,
  };

  const localeDate = now.toLocaleString('en-GB', options);
  const [datePart, timePart] = localeDate.split(', ');
  const [day, month, year] = datePart.split('/');
  const formattedDate = `${year}-${month}-${day}T${timePart}`;
  return formattedDate;
};
export default getTodayDatePlusOneDay;
