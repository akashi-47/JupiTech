"use client";
import { createContext, useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode"; // Correct import
import { useContext } from "react";
import { UserContextType } from "@/types/types";

// Define the custom type for your decoded JWT payload
interface DecodedJwtPayload {
  role: string;  // Assuming the role is a string
  exp: number;   // You can include other properties like exp, sub, etc.
  iat: number;
}

const UserContext = createContext<UserContextType | undefined>(undefined);

export const UserProvider = ({ children }: { children: React.ReactNode }) => {
  const [token, setToken] = useState<string>(""); // Store the JWT token
  const [role, setRole] = useState<string>(""); // Store the user role
  const [isLoggedIn, setIsLoggedIn] = useState(false); // State to track if the user is logged in

  // Check if there's a token in localStorage on component mount
  useEffect(() => {
    const storedToken = localStorage.getItem("token"); // Retrieve token from localStorage

    if (storedToken) {
      setToken(storedToken); // Set the token from localStorage
      try {
        // Decode the token and cast it to the custom type
        const decodedToken = jwtDecode<DecodedJwtPayload>(storedToken); // Decode the token
        const userRole = decodedToken.role;

        setRole(userRole); // Set the role
        setIsLoggedIn(true); 
        console.log(isLoggedIn)// Set logged in state to true
      } catch (error) {
        console.error("Error decoding token:", error);
      }
    }
  }, [isLoggedIn,token]); // This effect runs only once on component mount

  return (
    <UserContext.Provider value={{ token, role, setToken, isLoggedIn,setIsLoggedIn }}>
      {children}
    </UserContext.Provider>
  );
};

export const useUserContext = (): UserContextType => {
  const context = useContext(UserContext);
  if (context === undefined) {
    throw new Error("useUserContext must be used within a UserProvider");
  }
  return context; // Custom hook to use the UserContext
};
