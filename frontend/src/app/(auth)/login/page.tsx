"use client";
import { useState } from "react";
import axios from "axios";
// import { useRouter } from "next/navigation";
import { useAppContext } from "@/context/appContext";
import Link from "next/link";
import { useUserContext } from "@/context/userContext"; // Importing user context


export default function SignIn() {
  const [email, setEmail] = useState(""); // State for email
  const [password, setPassword] = useState(""); // State for password
  const [message, setMessage] = useState<string | null>(null); // Error or success message
  const [loading, setLoading] = useState(false); // Loading state
  const { backendUrl } = useAppContext();
const {role,setToken} = useUserContext(); // Accessing user context

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (name === "email") {
      setEmail(value);
    } else if (name === "password") {
      setPassword(value);
    }
  };

  const submitHandler = (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setMessage(null); // Reset message before submission

    // Use then/catch for Axios request handling
    axios
      .post(`${backendUrl}/auth/login`, { email, password })
      .then((response) => {
        const { accessToken } = response.data;
        console.log("ASD"); // Log the token for debugging
        console.log(accessToken);
        setToken(accessToken); // Log the token for debugging
        console.log(response.data); // Log the response for debugging

        // Store JWT in localStorage (or cookies if preferred)
        localStorage.setItem("token", accessToken);

        setMessage("Login successful!"); // Success message

        // Redirect to dashboard or another page after successful login
      
      })
      .catch((error) => {
        console.error(error);

        if (axios.isAxiosError(error) && error.response) {
          // Handling specific backend error messages
          setMessage(`${error.response.data.message || "Invalid credentials"}`);
        } else {
          setMessage("An unexpected error occurred, please try again.");
        }
      })
      .finally(() => {
        setLoading(false); // Stop loading after the request is complete
      });
  };
  return (
    <section>
      <div className="mx-auto max-w-6xl px-4 sm:px-6">
        <div className="py-12 md:py-20">
          {/* Section header */}
          <div className="pb-12 text-center">
            <h1 className="bg-gradient-to-r from-gray-300 via-indigo-300 to-gray-200 bg-clip-text text-3xl font-semibold text-transparent md:text-4xl">
              Welcome back
            </h1>
          </div>

          {/* Sign in form */}
          <form className="mx-auto max-w-sm" onSubmit={submitHandler}>
            <div className="space-y-5">
              <div>
                <label className="mb-1 block text-sm font-medium text-indigo-300" htmlFor="email">
                  Email
                </label>
                <input
                  id="email"
                  name="email"
                  type="email"
                  value={email}
                  onChange={handleInputChange}
                  className="w-full rounded border border-gray-300  px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                  placeholder="Your email"
                  required
                />
              </div>

              <div>
                <div className="mb-1 flex items-center justify-between">
                  <label className="block text-sm font-medium text-indigo-300" htmlFor="password">
                    Password
                  </label>
                  <Link className="text-sm text-gray-500 hover:underline" href="/reset-password">
                    Forgot?
                  </Link>
                </div>
                <input
                  id="password"
                  name="password"
                  type="password"
                  value={password}
                  onChange={handleInputChange}
                  className="w-full rounded border border-gray-300 px-3 py-2 text-sm shadow-sm focus:border-indigo-500 focus:outline-none focus:ring-1 focus:ring-indigo-500"
                  placeholder="Your password"
                  required
                />
              </div>
            </div>

            {/* Submit Button */}
            <div className="mt-6 space-y-5">
              <button
                type="submit"
                className="w-full rounded bg-indigo-600 px-4 py-2 text-white hover:bg-indigo-700"
                disabled={loading}
              >
                {loading ? "Signing in..." : "Sign in"}
              </button>

              <div className="flex items-center gap-3 text-sm italic text-gray-500">
                <div className="flex-1 border-t border-gray-300" />
                or
                <div className="flex-1 border-t border-gray-300" />
              </div>

              <button className="w-full rounded bg-gray-800 px-4 py-2 text-gray-100 hover:bg-gray-700">
                Sign In with Google  {role ? `(${role})` : ""}
              </button>
            </div>
          </form>

          {/* Display message (error or success) */}
          {message && (
            <div className={`mt-4 text-center ${message.startsWith("Error") ? "text-red-500" : "text-green-500"}`}>
              {message}
            </div>
          )}

          {/* Bottom link */}
          <div className="mt-6 text-center text-sm text-indigo-300">
            Dont have an account?{" "}
            <Link className="font-medium text-indigo-500 hover:underline" href="/signup">
              Sign Up
            </Link>
          </div>
        </div>
      </div>
    </section>
  );
}
