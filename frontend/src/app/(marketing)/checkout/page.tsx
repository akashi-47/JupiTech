"use client";
import "../../css/check.css";

import { useProductContext } from "@/context/productContext";
export default function Checkout() {


    const { total } = useProductContext();
    return (
      <div className="flex flex-col items-center justify-center min-h-screen py-6 ">
        <div className="w-full max-w-3xl bg-gray-900 shadow-lg rounded-lg p-6">
          <h2 className="text-3xl font-semibold mb-6">Checkout</h2>
  
          {/* Billing Information */}
          <div className="mb-8">
            <h3 className="text-xl font-semibold mb-4">Billing Information</h3>
            <form>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div>
                  <label htmlFor="firstName" className="block">First Name</label>
                  <input
                    id="firstName"
                    type="text"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 bg-unset"
                    placeholder="John"
                  />
                </div>
                <div>
                  <label htmlFor="lastName" className="block">Last Name</label>
                  <input
                    id="lastName"
                    type="text"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 bg-unset"
                    placeholder="Doe"
                  />
                </div>
                <div>
                  <label htmlFor="email" className="block">Email Address</label>
                  <input
                    id="email"
                    type="email"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 bg-unset"
                    placeholder="john.doe@example.com"
                  />
                </div>
                <div>
                  <label htmlFor="address" className="block">Shipping Address</label>
                  <input
                    id="address"
                    type="text"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 bg-unset"
                    placeholder="123 Main St"
                  />
                </div>
              </div>
            </form>
          </div>
  
          {/* Payment Method */}
          <div className="mb-8">
            <h3 className="text-xl font-semibold mb-4">Payment Method</h3>
            <form>
              <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                <div>
                  <label htmlFor="cardNumber" className="block">Card Number</label>
                  <input
                    id="cardNumber"
                    type="text"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 "
                    placeholder="1234 5678 9876 5432"
                  />
                </div>
                <div>
                  <label htmlFor="expiryDate" className="block">Expiry Date</label>
                  <input
                    id="expiryDate"
                    type="text"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 bg-unset"
                    placeholder="MM/YY"
                  />
                </div>
                <div>
                  <label htmlFor="cvv" className="block">CVV</label>
                  <input
                    id="cvv"
                    type="text"
                    className="mt-1 w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500 bg-unset"
                    placeholder="123"
                  />
                </div>
              </div>
            </form>
          </div>
  
          {/* Order Summary */}
          <div className="mb-8">
            <h3 className="text-xl font-semibold mb-4">Order Summary</h3>
            <div className="bg-gray-700 p-4 rounded-md shadow-sm">
              <div className="flex justify-between text-white">
                <span>Items Total:</span>
                <span className="font-semibold">${total}</span>
              </div>
              <div className="flex justify-between text-white">
                <span>Shipping:</span>
                <span className="font-semibold">$10</span>
              </div>
              <div className="flex justify-between text-white">
                <span className="font-semibold">Total:</span>
                <span className="font-semibold text-indigo-600">${total+10}</span>
              </div>
            </div>
          </div>
  
          {/* Submit Button */}
          <div className="flex justify-center">
            <button className="bg-indigo-600 text-white px-6 py-3 rounded-md text-lg hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-opacity-50">
              Complete Checkout
            </button>
          </div>
        </div>
      </div>
    );
  }
  