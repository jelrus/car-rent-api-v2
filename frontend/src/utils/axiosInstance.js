import axios from 'axios';
const API_URL=import.meta.env.VITE_API_KEY
const axiosInstance = axios.create({
  baseURL: `${API_URL}/api/v1/`,
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosInstance.interceptors.request.use(config => {
  const token = sessionStorage.getItem('token');
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

export default axiosInstance;