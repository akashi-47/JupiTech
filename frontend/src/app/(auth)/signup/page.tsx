"use client";
import Link from "next/link";
import axios from "axios";
import { useState } from "react";
import { useAppContext } from "@/context/appContext";
import { useRouter, useSearchParams } from "next/navigation";
import "../../css/auth.css";

interface FormData {
  nom: string;
  email: string;
  password: string;
  address: string;
  businessname?: string;  // For sELLER, this will be optional
  role: "CLIENT" | "SELLER";
}

export default function SignUp() {
  const searchParams = useSearchParams();
  const defaultRole = searchParams.get("role") === "SELLER" ? "SELLER" : "CLIENT";

  const [formData, setFormData] = useState<FormData>({
    nom: "",
    email: "",
    password: "",
    address: "",
    role: defaultRole,
  });
  const router = useRouter(); // For navigation after successful registration
  const [message, setMessage] = useState<string | null>(null); // To display error/success messages
  const [loading, setLoading] = useState(false); // To handle loading state

  const { backendUrl } = useAppContext();

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleRoleChange = (type: "CLIENT" | "SELLER") => {
    setFormData((prevData) => ({
      ...prevData,
      role: type,
    }));
  };

  const submitHandler = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true); // Start loading
    setMessage(null); // Reset message before sending request

    try {
      const response = await axios.post(`${backendUrl}/auth/register`, formData);
      console.log(response.data);
      setMessage("Registration successful! Please log in.");
      router.push("/login");

       // Success message
      // Optionally redirect to another page or login
    } catch (error) {
      console.error(error);
      // Handle error (e.g., show error message)
      if (axios.isAxiosError(error) && error.response) {
        setMessage(`${error.response.data || 'Something went wrong'}`); // Display the error from the server
      } else {
        setMessage("An error occurred, please try again later.");
      }
    } finally {
      setLoading(false); // Stop loading once the request is complete
    }
  };

  return (
    <section>
      <div className="mx-auto max-w-6xl px-4 sm:px-6">
        <div className="py-12 md:py-20">
          {/* Section header */}
          <div className="pb-12 text-center">
            <h1 className="bg-gradient-to-r from-gray-300 via-indigo-300 to-gray-200 bg-clip-text text-3xl font-semibold text-transparent md:text-4xl">
              Create your account
            </h1>
          </div>

          {/* Account type selector */}
          <div className="mx-auto mb-8 max-w-lg">
            <div className="mb-3 text-center text-sm font-medium text-indigo-300">
              I want to register as:
            </div>
            <div className="flex rounded-lg border border-gray-300 p-1">
              <button
                className={`flex-1 rounded-md py-3 transition-all duration-200 ${
                  formData.role === 'CLIENT'
                    ? 'bg-indigo-600 text-white shadow-md'
                    : 'text-white hover:bg-indigo-500 '
                }`}
                onClick={() => handleRoleChange('CLIENT')}
              >
                Client
              </button>
              <button
                className={`flex-1 rounded-md py-3 transition-all duration-200 ${
                  formData.role === 'SELLER'
                    ? 'bg-orange-500 text-white shadow-md'
                    : 'text-white hover:bg-orange-600'
                }`}
                onClick={() => handleRoleChange('SELLER')}
              >
                
                

                SELLER
              </button>
            </div>
            <div className="mt-2 text-center text-xs text-white">
              {formData.role === 'SELLER'
                ? "Register as a client to discover and purchase products"
                : "Register as a sELLER to list and sell your products"}
            </div>
          </div>

          {/* Sign up form */}
          <form className="mx-auto max-w-lg" onSubmit={submitHandler}>
            <div className="space-y-5">
              <div>
                <label className="mb-1 block text-sm font-medium text-indigo-300" htmlFor="fname">
                  Full Name
                </label>
                <input
                  id="fname"
                  type="text"
                  name="nom"
                  value={formData.nom}
                  onChange={handleInputChange}
                  className="w-full rounded border border-gray-300 px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                  placeholder="Your full name"
                />
              </div>
              <div>
                <label className="mb-1 block text-sm font-medium text-indigo-300" htmlFor="email">
                  Email
                </label>
                <input
                  id="email"
                  type="email"
                  name="email"
                  value={formData.email}
                  onChange={handleInputChange}
                  className="w-full rounded border border-gray-300 px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                  placeholder="Your email"
                />
              </div>
              <div>
                <label className="mb-1 block text-sm font-medium text-indigo-300" htmlFor="password">
                  Password
                </label>
                <input
                  id="password"
                  type="password"
                  name="password"
                  value={formData.password}
                  onChange={handleInputChange}
                  className="w-full rounded border border-gray-300 px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                  placeholder="Create a password"
                />
              </div>
              <div>
                <label className="mb-1 block text-sm font-medium text-indigo-300" htmlFor="address">
                  Address
                </label>
                <input
                  id="address"
                  type="text"
                  name="address"
                  value={formData.address}
                  onChange={handleInputChange}
                  className="w-full rounded border border-gray-300 px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                  placeholder="Your address"
                />
              </div>

              {/* Conditional fields based on user type */}
              {formData.role === 'SELLER' && (
                <div className="space-y-5 rounded-lg border border-indigo-200 p-4">
                  <div className="text-sm font-medium text-indigo-500">SELLER Information</div>
                  <div>
                    <label className="mb-1 block text-sm font-medium text-indigo-300" htmlFor="businessName">
                      Business Name
                    </label>
                    <input
                      id="businessName"
                      type="text"
                      name="businessname"
                      value={formData.businessname || ""}
                      onChange={handleInputChange}
                      className="w-full rounded border border-gray-300 px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                      placeholder="Your business name"
                    />
                  </div>
                </div>
              )}
            </div>

            <div className="mt-6 space-y-5">
              <button
                type="submit"
                className={`w-full rounded px-4 py-2 text-white hover:bg-indigo-700
    ${formData.role === 'SELLER' ? 'bg-orange-500 hover:bg-orange-600' : 'bg-indigo-600'}
  `}
                disabled={loading}
              >
                {loading
                  ? "Signing up..."
                  : formData.role === 'CLIENT'
                    ? 'Sign Up as Client'
                    : <span className="text-white">Sign Up as SELLER</span>
                }
              </button>

              <div className="flex items-center gap-3 text-sm italic text-gray-500">
                <div className="flex-1 border-t border-gray-300" />
                or
                <div className="flex-1 border-t border-gray-300" />
              </div>

              <button className="w-full rounded bg-gray-800 px-4 py-2 text-gray-100 hover:bg-gray-700">
                Sign Up with Google
              </button>
            </div>
          </form>

          {/* Bottom link */}
          <div className="mt-6 text-center text-sm text-indigo-300">
            Already have an account?{" "}
            <Link className="font-medium text-indigo-500 hover:underline" href="/signin">
              Sign In
            </Link>
          </div>
        </div>
      </div>

      {/* Display Message */}
      {message && (
        <div className={`mt-4 text-center ${message.startsWith('Error') ? 'text-red-500' : 'text-green-500'}`}>
          {message}
        </div>
      )}
    </section>
  );
}
