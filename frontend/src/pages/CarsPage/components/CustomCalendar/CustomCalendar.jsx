import PropTypes from 'prop-types';
import { useState, useMemo } from 'react';
import './CustomCalendar.css';

const CustomCalendar = ({
  bookedDays,
  onDateSelect,
  onTimeSelect,
  selectedDates,
}) => {
  const [currentMonthLeft, setCurrentMonthLeft] = useState(new Date());
  const [currentMonthRight, setCurrentMonthRight] = useState(
    new Date(new Date().getFullYear(), new Date().getMonth() + 1),
  );

  const [range, setRange] = useState({ start: null, end: null });

  const generateDays = (month) => {
    const start = new Date(month.getFullYear(), month.getMonth(), 1); // Перший день місяця
    const startDay = start.getDay(); // День тижня для першого дня місяця

    const days = [];

    // Додаємо порожні дні для вирівнювання початку місяця з правильним днем тижня
    for (let i = 0; i < startDay; i++) {
      days.push(null); // Порожні елементи для попередніх днів
    }

    // Генеруємо всі дні місяця
    const end = new Date(month.getFullYear(), month.getMonth() + 1, 0); // Останній день місяця
    for (
      let day = new Date(start);
      day <= end;
      day.setDate(day.getDate() + 1)
    ) {
      days.push(new Date(day.getTime())); // Додаємо кожен день
    }

    return days;
  };

  const isSameDay = (date1, date2) => {
    return (
      date1 &&
      date2 &&
      date1.getFullYear() === date2.getFullYear() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getDate() === date2.getDate()
    );
  };

  const isDateBooked = (day) => {
    // Перевіряємо, чи day не є null перед тим, як виконувати операції з датою
    if (!day) return false;

    const formattedDate = new Date(
      day.getTime() - day.getTimezoneOffset() * 60000,
    )
      .toISOString()
      .split('T')[0];

    return bookedDays.some((bookedDay) => bookedDay.startsWith(formattedDate));
  };

  const isTimeBooked = (day, time) => {
    if (!day) return false;
    const formattedDate = day.toISOString().split('T')[0];
    const bookedDay = bookedDays.find(
      (bookedDay) => bookedDay.date === formattedDate,
    );
    return bookedDay?.times?.includes(time) || false;
  };

  const isInRange = (day) => {
    if (range.start && range.end) {
      return day >= range.start && day <= range.end;
    }
    return false;
  };

  const handleDayClick = (day) => {
    if (isSameDay(day, range.start) && isSameDay(day, range.end)) {
      setRange({ start: null, end: null });
      onDateSelect('pickup', null);
      onDateSelect('dropOff', null);
      return;
    }
    if (!range.start || (range.start && range.end)) {
      setRange({ start: day, end: null });
      onDateSelect('pickup', day);
    } else if (range.start && !range.end) {
      if (isSameDay(day, range.start)) {
        setRange({ ...range, end: day });
        onDateSelect('dropOff', day);
      } else if (day > range.start) {
        const selectedRange = [];
        for (
          let d = new Date(range.start);
          d <= day;
          d.setDate(d.getDate() + 1)
        ) {
          selectedRange.push(new Date(d).toISOString().split('T')[0]);
        }

        const hasConflict = selectedRange.some((selectedDate) =>
          bookedDays.includes(selectedDate),
        );

        if (hasConflict) {
          alert(
            'The selected range includes already booked dates. Please choose another range.',
          );
          return;
        }

        setRange({ ...range, end: day });
        onDateSelect('dropOff', day);
      } else {
        alert('Drop-off date cannot be earlier than the pick-up date.');
      }
    }
  };

  const formatTime = (hour, minute = 0) => {
    const ampm = hour < 12 ? 'AM' : 'PM';
    const formattedHour = hour % 12 === 0 ? 12 : hour % 12;
    return `${String(formattedHour).padStart(2, '0')}:${String(minute).padStart(2, '0')}${ampm}`;
  };

  const daysFirstMonth = useMemo(
    () => generateDays(currentMonthLeft),
    [currentMonthLeft],
  );
  const daysSecondMonth = useMemo(
    () => generateDays(currentMonthRight),
    [currentMonthRight],
  );

  const timeOptions = useMemo(() => {
    const options = [];
    for (let hour = 0; hour < 24; hour++) {
      for (let minute = 0; minute < 60; minute += 30) {
        options.push(formatTime(hour, minute));
      }
    }
    return options;
  }, []);

  return (
    <div className="calendar-container">
      <div className="calendar-header">
        <button
          onClick={() => {
            setCurrentMonthLeft(
              new Date(
                currentMonthLeft.getFullYear(),
                currentMonthLeft.getMonth() - 1,
              ),
            );
            setCurrentMonthRight(
              new Date(
                currentMonthRight.getFullYear(),
                currentMonthRight.getMonth() - 1,
              ),
            );
          }}
        >
          {'<'}
        </button>
        <button
          onClick={() => {
            setCurrentMonthLeft(
              new Date(
                currentMonthLeft.getFullYear(),
                currentMonthLeft.getMonth() + 1,
              ),
            );
            setCurrentMonthRight(
              new Date(
                currentMonthRight.getFullYear(),
                currentMonthRight.getMonth() + 1,
              ),
            );
          }}
        >
          {'>'}
        </button>
      </div>

      <div className="calendar-grid-container">
        <div className="calendar">
          <div className="calendar-title">
            {currentMonthLeft.toLocaleString('en-US', {
              month: 'long',
              year: 'numeric',
            })}
          </div>
          <div className="calendar-grid">
            {['S', 'M', 'T', 'W', 'T', 'F', 'S'].map((day, index) => (
              <div key={index} className="calendar-day-name">
                {day}
              </div>
            ))}
            {daysFirstMonth.map((day, index) => (
              <div
                key={index}
                onClick={() => handleDayClick(day)}
                className={`calendar-day ${
                  day && isSameDay(day, range.start) ? 'start' : ''
                } ${day && isSameDay(day, range.end) ? 'end' : ''} ${
                  day &&
                  !isSameDay(day, range.start) &&
                  !isSameDay(day, range.end) &&
                  isInRange(day)
                    ? 'in-range'
                    : ''
                } ${day && isDateBooked(day) ? 'booked' : ''}`}
              >
                {day ? day.getDate() : ''}
              </div>
            ))}
          </div>
        </div>

        <div className="calendar">
          <div className="calendar-title">
            {currentMonthRight.toLocaleString('en-US', {
              month: 'long',
              year: 'numeric',
            })}
          </div>
          <div className="calendar-grid">
            {['S', 'M', 'T', 'W', 'T', 'F', 'S'].map((day, index) => (
              <div key={index} className="calendar-day-name">
                {day}
              </div>
            ))}
            {daysSecondMonth.map((day, index) => (
              <div
                key={index}
                onClick={() => handleDayClick(day)}
                className={`calendar-day ${
                  day && isSameDay(day, range.start) ? 'start' : ''
                } ${day && isSameDay(day, range.end) ? 'end' : ''} ${
                  day &&
                  !isSameDay(day, range.start) &&
                  !isSameDay(day, range.end) &&
                  isInRange(day)
                    ? 'in-range'
                    : ''
                } ${day && isDateBooked(day) ? 'booked' : ''}`}
              >
                {day ? day.getDate() : ''}
              </div>
            ))}
          </div>
        </div>
      </div>

      <div className="time-picker">
        <div>
          <label>Pick-up time</label>
          <select
            value={selectedDates?.pickup?.time || '07:00AM'}
            onChange={(e) => onTimeSelect('pickup', e.target.value)}
          >
            {timeOptions.map((time) => (
              <option
                key={time}
                value={time}
                disabled={
                  selectedDates.pickup?.date
                    ? isTimeBooked(selectedDates.pickup.date, time)
                    : false
                }
              >
                {time}
              </option>
            ))}
          </select>
        </div>
        <div>
          <label>Drop-off time</label>
          <select
            value={selectedDates?.dropOff?.time || '10:00AM'}
            onChange={(e) => onTimeSelect('dropOff', e.target.value)}
          >
            {timeOptions.map((time) => (
              <option
                key={time}
                value={time}
                disabled={
                  selectedDates.pickup?.date
                    ? isTimeBooked(selectedDates.pickup.date, time)
                    : false
                }
              >
                {time}
              </option>
            ))}
          </select>
        </div>
      </div>
    </div>
  );
};

CustomCalendar.propTypes = {
  onDateSelect: PropTypes.func.isRequired,
  onTimeSelect: PropTypes.func.isRequired,
  selectedDates: PropTypes.shape({
    pickup: PropTypes.shape({
      date: PropTypes.instanceOf(Date),
      time: PropTypes.string,
    }),
    dropOff: PropTypes.shape({
      date: PropTypes.instanceOf(Date),
      time: PropTypes.string,
    }),
  }).isRequired,
  bookedDays: PropTypes.arrayOf(
    PropTypes.shape({
      date: PropTypes.string,
      times: PropTypes.arrayOf(PropTypes.string),
    }),
  ),
};

export default CustomCalendar;
