import React, { createContext, useContext, ReactNode } from "react";
import { Product } from "@/types/types"; // Assuming you have a Product type defined in your types file
import { useState, useEffect } from "react";
import axios from "axios";

// Define the type of the context value
interface AppContextType {
  backendUrl: string;
  products: Product[];
  loading: boolean;
  getProduct : (id:number) => Promise<Product | undefined>;
}

// Create the context with a default value for backendUrl
const AppContext = createContext<AppContextType | undefined>(undefined);

// AppProvider component simply wraps children with context provider
export const AppProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const backendUrl = "http://localhost:8083";
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(false);
 // const [token, setToken] = useState<string | null>(null); // Assuming you have a token state
  const fetchProducts = async () => {
    setLoading(true);
    try {
      const response = await axios.get(`${backendUrl}/api/produits/all`);
      setProducts(response.data);
      console.log(response.data); // Log the response data for debugging
    } catch (error) {
      console.error("Error fetching products:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
   // setToken(localStorage.getItem("token")); // Fetch token from local storage
  
    fetchProducts();
  }, []);
  const getProduct = async (id: number): Promise<Product | undefined> => {
    try {
      const response = await axios.get<Product>(`${backendUrl}/api/produits/${id}`);
      return response.data;
    } catch (error) {
      console.error("Error fetching product:", error);
      return undefined;
    }
  }
  return (
    <AppContext.Provider value={{ backendUrl, products, loading,getProduct }}>
      {children}
    </AppContext.Provider>
  );
};

// Custom hook to use the context
export const useAppContext = (): AppContextType =>{
  const context = useContext(AppContext);
  if (!context) {
    throw new Error("useAppContext must be used within an AppProvider");
  }
  return context;
}

export default AppContext;
