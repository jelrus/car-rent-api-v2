import './BookingsPage.css';
import { useParams, Link, useNavigate } from 'react-router';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { getBookings } from '@/redux/slices/bookingsSlice';
import Button from '@/components/atoms/Button/Button';
import chatIcon from '@/assets/chat-icon.png';
import classNames from 'classnames';

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
  if (error) return <p>something went wrong</p>;

  return (
    <div className='bookings-page'>
      <div className='bookings-title'>
        <h2>My bookings</h2>
      </div>

      <div className='bookings-filter'>
        <ul className='bookings-navbar'>
          {tabs.map(({ id, path, status, title }) => (
            <li
              key={id}
              className={classNames('bookings-navitem', { 'is-active': path === tabId })}
              onClick={() => handleFilterChange(status)}
            >
              <Link to={`/bookings/${path}`} className='bookings-navlink'>{title}</Link>
            </li>
          ))}
        </ul>
      </div>

      <div className='bookings-list'>
        {filteredBookings.length === 0 ? (
          <div>
            You have not placed an order yet
          </div>
        ) : filteredBookings.map((booking) => {
          const { title } = tabs.find(tab => tab.status === booking.bookingStatus) || '';

          return (
            <div key={booking.bookingId} className='bookings-card'>
              <img src={booking.carImageUrl} alt={booking.carModel} />
              <p>{title}</p>
              <h2>{booking.carModel}</h2>
              <span>{booking.orderDetails}</span>
              <div className='card-container' >
                {booking.bookingStatus === 'RESERVED' && (
                  <div className='card-buttons'>
                    <Button
                      text='Cancel'
                      type='secondary'
                      // onClick={handleCancel}
                      disabled={loading}
                    />
                    <Button
                      text='Edit'
                      type='primary'
                      // onClick={handleEdit}
                      disabled={loading}
                    />
                  </div>  
                )} 
              </div> 
              {(booking.bookingStatus === 'RESERVED' 
              || booking.bookingStatus === 'STARTED' 
              || booking.bookingStatus === 'CANCELLED')
              && (
                <span>
                  Have any questions?&nbsp;&nbsp;
                  <a href="#">
                    Support chat&nbsp;&nbsp;
                    <img className='card-icon' src={chatIcon} alt="chat-icon" />
                  </a>
                  
                </span>
              )
              }
            </div>
          )
        } )}
      </div>
    </div>
  );
};

export default BookingsPage;
