import axios from 'axios';

// Create axios instance with base URL
const api = axios.create({
  baseURL: 'http://3.211.201.146:8082/api/order', // Change this to your backend URL
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json',
  }
});

// Add token to requests if it exists
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('authToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Handle response errors
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('authToken');
      localStorage.removeItem('user');
      window.location.href = '/';
    }
    return Promise.reject(error);
  }
);

// Order APIs
export const ordersAPI = {

    createOrder: async (orderData) => {
        const response = await api.post('/create', orderData);
        return response.data;
    },

    getOrderById: async (id) => {
        const response = await api.get(`/orders/${id}`);
        return response.data;
    },

    getAllOrders: async () => {
        const response = await api.get('');
        return response.data;
    },

    updateOrder: async (id, orderData) => {
        const response = await api.put(`/orders/${id}`, orderData);
        return response.data;
    },

   
};

