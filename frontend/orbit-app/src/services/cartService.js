import axios from 'axios';

// Create axios instance with base URL
const api = axios.create({
  baseURL: 'http://cart-service/api/cart', // Change this to your backend URL
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

// Cart APIs
export const cartsAPI = {

    proceedToCheckout: async (items) => {
      try {
        const response = await api.post('/checkout', { items });
        return response;
      } catch (error) {
        return {success: false, error: error.response?.data?.message || 'Checkout failed' };
      }
    },
    getPricing: async () => {
        const response = await api.get('/pricing');
        return response;
    },
    proceedToBuy: async () => {
      try {
        const response = await api.post('/proceedToBuy');
        return response;
      } catch (error) {
        return {success: false, error: error.response?.data?.message || 'Buy process failed' };
      }
    },
    getAllProducts: async (page, productCount) => {
        const response = await api.get('/products', {
            params: { page, productCount }
        });
        return response.data;
    },

    getProductById: async (id) => {
        const response = await api.get(`/products/${id}`);
        return response.data;
    },

    createProduct: async (productData) => {
        const response = await api.post('/products', productData);
        return response.data;
    },

  updateProduct: async (id, productData) => {
    const response = await api.put(`/products/${id}`, productData);
    return response.data;
  },

  deleteProduct: async (id) => {
    const response = await api.delete(`/products/${id}`);
    return response.data;
  }
};

