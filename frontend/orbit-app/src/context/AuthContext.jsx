import React, { createContext, useState, useContext, useEffect } from 'react';
import { authAPI } from '../services/api';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    // Check if user is logged in on mount
    const storedUser = localStorage.getItem('user');
    const token = localStorage.getItem('authToken');
    
    if (storedUser && token) {
      setUser(JSON.parse(storedUser));
      setIsAuthenticated(true);
    }
    setLoading(false);
  }, []);

  const login = async (email, password) => {
    try {
      const response = await authAPI.login(email, password);
      const { loggedIn, token, userDetails } = response;
      if (loggedIn) {
        localStorage.setItem('authToken', token);
      }
      else{
        return { success: false, error: response.message || 'Login failed' };
      }
      
      setUser(userDetails);
      setIsAuthenticated(true);
      
      return { success: true };
    } catch (error) {
      return { success: false, error: error.response?.data?.message || 'Login failed' };
    }
  };

  const register = async (name, mobile, email, password) => {
    try {
      const response = await authAPI.register(name, mobile, email, password);
     
      if(response){
       return { success: true };
      }
      else {
       return { success: false, error: 'Registration failed' };
      }
      
    } catch (error) {
      return { success: false, error: error.response?.data?.message || 'Registration failed' };
    }
  };

  const getOrders = async () => {
    try {
      const response = await authAPI.getOrders();
      return response;
    } catch (error) {
      console.error('Error fetching orders:', error);
      return { success: false, error: error.message || 'Failed to fetch orders' };
    }
  };

  const logout = () => {
    authAPI.logout();
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
    setUser(null);
    setIsAuthenticated(false);
  };

  const updateUser = (updatedUser) => {
    setUser(updatedUser);
    localStorage.setItem('user', JSON.stringify(updatedUser));
  };

  return (
    <AuthContext.Provider value={{
      user,
      isAuthenticated,
      loading,
      login,
      register,
      logout,
      getOrders,
      updateUser
    }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
};
