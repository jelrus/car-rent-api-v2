import './BookingsPage.css';
import { useParams, Link, useNavigate } from 'react-router';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getBookings } from '@/redux/slices/bookingsSlice';

const tabs = [
  { id: 'tab-1', path: 'all', status: 'ALL', title: 'All bookings' },
  { id: 'tab-2', path: 'reserved', status: 'RESERVED', title: 'Reserved' },
  { id: 'tab-3', path: 'started', status: 'STARTED', title: 'Service started' },
  { id: 'tab-4', path: 'provided', status: 'PROVIDED', title: 'Service provided' },
  { id: 'tab-5', path: 'finished', status: 'FINISHED', title: 'Booking finished' },
  { id: 'tab-6', path: 'cancelled', status: 'CANCELLED', title: 'Cancelled' },
];

const BookingsPage = () => {
  const { tabId } = useParams();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const { userId } = useSelector((state) => state.auth.user);
  const { bookings, loading, error } = useSelector((state) => state.bookings);

  const [filteredBookings, setFilteredBookings] = useState([]);
  const [statusFilter, setStatusFilter] = useState('ALL');

  useEffect(() => {
    if (userId) {
      dispatch(getBookings(userId));
    } else {
      console.log("UserId is null, please log in");
    }
  }, [dispatch, userId]);

  useEffect(() => {
    const currentTab = tabs.find((tab) => tab.path === tabId);

    if (currentTab) {
      setStatusFilter(currentTab.status);
    } else {
      navigate('/bookings/all', { replace: true });
    }
  }, [tabId, navigate]);

  useEffect(() => {
    if (statusFilter === 'ALL') {
      setFilteredBookings(bookings);
    } else {
      setFilteredBookings(bookings.filter((booking) => booking.bookingStatus === statusFilter));
    }
  }, [bookings, statusFilter]);

  const handleFilterChange = (filter) => {
    const tab = tabs.find((tab) => tab.status === filter);
    if (tab) {
      navigate(`/bookings/${tab.path}`);
    }
  };

  if (loading) return <p>Loading bookings...</p>;
  if (error) return <p>Error: {error}</p>;

  return (
    <div className='bookings-page'>
      <div className='bookings-title'>
        <h2>My bookings</h2>
      </div>

      <div className='bookings-filter'>
        <ul className='tabs-list'>
          {tabs.map(({ id, path, status, title }) => (
            <li
              key={id}
              className={`tab-item ${path === tabId ? 'is-active' : ''}`}
              onClick={() => handleFilterChange(status)}
            >
              <Link to={`/bookings/${path}`} className='tab-link'>{title}</Link>
            </li>
          ))}
        </ul>
      </div>

      <div className='bookings-list'>
        {filteredBookings.map((booking) => (
          <li key={booking.bookingId} className='bookings-card'>
            <img src={booking.carImageUrl} alt={booking.carModel} />
            <div>
              <h2>{booking.carModel}</h2>
              <p>{booking.orderDetails}</p>
              <p>Status: {booking.bookingStatus}</p>
            </div>
          </li>
        ))}
      </div>
    </div>
  );
};

export default BookingsPage;
