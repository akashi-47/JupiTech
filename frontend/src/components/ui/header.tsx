"use client";
import Link from "next/link";
import Logo from "./logo";
import { useState, useEffect } from "react";
import { FaShoppingCart } from "react-icons/fa";
import "../../app/css/header.css"; // We will use react-icons for the cart icon
import { useProductContext } from "@/context/productContext";
import { useUserContext } from "@/context/userContext"; // Import the useUserContext hook
import { useRouter } from "next/navigation"; // Import useRouter for navigation

export default function Header() {
  const { numberProducts } = useProductContext();
  const [cartItems, setCartItems] = useState(0);

  // Get the authentication status from UserContext
  const { isLoggedIn, setIsLoggedIn, setToken } = useUserContext();

  const router = useRouter(); // Initialize router for page redirection

  // This will run once when the component mounts
  useEffect(() => {
    setCartItems(numberProducts);
  }, [numberProducts]);

  // Handle logout logic
  const handleLogout = () => {
    // Clear the token from localStorage
    localStorage.removeItem("token");

    // Update the isLoggedIn state to false
    setIsLoggedIn(false);

    // Clear the token in the context as well
    setToken("");

    // Redirect the user to the homepage or login page after logout
    router.push("/login");
  };

  return (
    <header className="z-30 mt-2 w-full md:mt-5">
      <div className="mx-auto max-w-7xl px-4 sm:px-6">
        <div className="relative flex h-14 items-center justify-between gap-3 rounded-2xl bg-gray-900 px-3">
          {/* Site branding */}
          <div className="flex items-center">
            <Logo />
          </div>

          <ul className="flex items-center justify-center gap-3 md:gap-6">
            <li className="hidden md:block">
              <Link href="/" className="text-gray-300 hover:text-white">
                Home
              </Link>
            </li>
            <li className="hidden md:block">
              <Link href="/products" className="text-gray-300 hover:text-white">
                Products
              </Link>
            </li>
            <li className="hidden md:block">
              <Link href="/about" className="text-gray-300 hover:text-white">
                About Us
              </Link>
            </li>
            <li className="hidden md:block">
              <Link href="/contact" className="text-gray-300 hover:text-white">
                Contact Us
              </Link>
            </li>
          </ul>

          {/* Desktop sign-in links */}
          <ul className="flex items-center justify-end gap-3">
            {/* Conditionally render the Sign In, Register, and Logout buttons */}
            {!isLoggedIn ? (
              <>
                <li className="signin">
                  <Link href="/login" className="btn-sm">
                    Sign In
                  </Link>
                </li>
                <li>|</li>
                <li className="register">
                  <Link href="/signup" className="btn-sm">
                    Register
                  </Link>
                </li>
              </>
            ) : (
              // Render the Logout button if the user is logged in
              <li className="logout">
                <button
                  onClick={handleLogout}
                  className="btn-sm text-red-500 hover:text-red-700"
                >
                  Log Out
                </button>
              </li>
            )}

            {/* Cart Icon */}
            <li className="relative">
              <Link
                // This is just a test for adding items, you can remove this or modify it to fit your app's logic
                className="flex items-center text-gray-300 hover:text-white cursor-pointer"
                href="/cart"
              >
                <FaShoppingCart size={24} />
                {/* Cart item count */}
                {cartItems > 0 && <span className="sign">{cartItems}</span>}
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </header>
  );
}
