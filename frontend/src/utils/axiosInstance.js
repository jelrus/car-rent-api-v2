import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'https://hcxkaw6gl9.execute-api.eu-west-1.amazonaws.com/api/v1',
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosInstance.interceptors.request.use(config => {
  const token = sessionStorage.getItem('token'); // Отримати актуальний токен
  if (token) {
    config.headers['Authorization'] = `Bearer ${token}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

export default axiosInstance;