import { useEffect } from 'react';
import { useNavigate } from 'react-router';
import { useSelector } from 'react-redux';

const checkAuthRole = (WrappedComponent, allowedRoles = []) => {
  return (props) => {
    const navigate = useNavigate();
    const user = useSelector((state) => state.auth.user);
    const token = useSelector((state) => state.auth.token);

    useEffect(() => {
      if (!token || !allowedRoles.includes(user.role)) {
        navigate('/cars');
      }
    }, [token, user, allowedRoles, navigate]);

    if (!token || !allowedRoles.includes(user.role)) {
      return null;
    }

    return <WrappedComponent {...props} />;
  };
};

export default checkAuthRole;
