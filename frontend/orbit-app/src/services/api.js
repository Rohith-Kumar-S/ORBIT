import axios from 'axios';

// Create axios instance with base URL
const api = axios.create({
  baseURL: '', // Change this to your backend URL
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

// Auth APIs
export const authAPI = {
  login: async (email, password) => {
    console.log('API Login called with:', email, password);
    const response = await api.post('http://user-service:8080/api/auth/user/login', {
      email,
      password
    });
    console.log('API Login response:', response);
    return response.data;
  },
  
  register: async (name, mobile, email, password) => {
    console.log('API Register called with:', name, mobile, email, password);
    const response = await api.post('http://user-service:8080/api/auth/user/register', {
      name,
      mobileNumber: mobile,
      email,
      password
    });
    console.log('API Register response:', response);
    return response.data;
  },
  
  logout: async () => {
    // Dummy implementation
    return Promise.resolve();
    // Real implementation:
    // return api.post('/auth/logout');
  },
  
  changePassword: async (currentPassword, newPassword) => {
    // Dummy implementation
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({ data: { message: 'Password changed successfully' } });
      }, 1000);
    });
    // Real implementation:
    // return api.put('/auth/change-password', { currentPassword, newPassword });
  }
};

// Orders APIs
export const ordersAPI = {
  getOrders: async () => {
    // Dummy implementation
    const response = await api.get('http://order-service:8082/api/order');
    if(response.data.status){
    return response.data;
    }
    else{
      return {"data":[]};
    }
  },
  
  getOrderById: async (orderId) => {
    // Real implementation:
    // return api.get(`/orders/${orderId}`);
  }
};

// Orbit Pay APIs
export const orbitPayAPI = {
  getBalance: async () => {
    // Dummy implementation
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({ data: { balance: 150.00 } });
      }, 500);
    });
    // Real implementation:
    // return api.get('/orbit-pay/balance');
  },
  
  addFunds: async (amount, paymentMethod) => {
    // Dummy implementation
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({ 
          data: { 
            newBalance: 150.00 + parseFloat(amount),
            transactionId: 'TXN-' + Date.now()
          } 
        });
      }, 1500);
    });
    // Real implementation:
    // return api.post('/orbit-pay/add-funds', { amount, paymentMethod });
  },
  
  getTransactions: async () => {
    // Dummy implementation
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          data: [
            { id: 1, date: '2024-10-20', type: 'Credit', amount: 50.00, description: 'Added funds' },
            { id: 2, date: '2024-10-19', type: 'Debit', amount: 89.99, description: 'Purchase: Wireless Headphones' },
            { id: 3, date: '2024-10-15', type: 'Credit', amount: 100.00, description: 'Added funds' }
          ]
        });
      }, 1000);
    });
    // Real implementation:
    // return api.get('/orbit-pay/transactions');
  }
};

// Support APIs
export const supportAPI = {
  submitTicket: async (subject, message) => {
    // Dummy implementation
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({ 
          data: { 
            ticketId: 'TICK-' + Date.now(),
            message: 'Your ticket has been submitted successfully'
          } 
        });
      }, 1000);
    });
    // Real implementation:
    // return api.post('/support/tickets', { subject, message });
  },
  
  getTickets: async () => {
    // Dummy implementation
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve({
          data: [
            { 
              id: 1, 
              ticketId: 'TICK-001', 
              subject: 'Product not received', 
              status: 'Open', 
              date: '2024-10-19' 
            },
            { 
              id: 2, 
              ticketId: 'TICK-002', 
              subject: 'Refund request', 
              status: 'Resolved', 
              date: '2024-10-10' 
            }
          ]
        });
      }, 1000);
    });
    // Real implementation:
    // return api.get('/support/tickets');
  }
};

export default api;
