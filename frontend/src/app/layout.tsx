"use client";
import "./css/style.css";

import { Inter } from "next/font/google";
import localFont from "next/font/local";

import Header from "@/components/ui/header";
import Footer from "@/components/ui/footer";
import { usePathname } from "next/navigation";
import { ProductProvider } from "../context/productContext";
import SearchBar from "@/components/helpersComponents/SearchBar";
import { AppProvider } from "@/context/appContext";
import { UserProvider } from "@/context/userContext";



const inter = Inter({
  subsets: ["latin"],
  variable: "--font-inter",
  display: "swap",
});

const nacelle = localFont({
  src: [
    {
      path: "../public/fonts/nacelle-regular.woff2",
      weight: "400",
      style: "normal",
    },
    {
      path: "../public/fonts/nacelle-italic.woff2",
      weight: "400",
      style: "italic",
    },
    {
      path: "../public/fonts/nacelle-semibold.woff2",
      weight: "600",
      style: "normal",
    },
    {
      path: "../public/fonts/nacelle-semibolditalic.woff2",
      weight: "600",
      style: "italic",
    },
  ],
  variable: "--font-nacelle",
  display: "swap",
});



export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {


  const pathname  = usePathname();
  const showSearchBar = pathname === '/products' || pathname.startsWith('/products/') || pathname === '/cart';
  return (
    <html lang="en">
      <body
        className={`${inter.variable} ${nacelle.variable} bg-gray-950 font-inter text-base text-gray-200 antialiased`}
      >
        <div className="flex min-h-screen flex-col overflow-hidden supports-[overflow:clip]:overflow-clip">

          <ProductProvider>
            <AppProvider>
              <UserProvider>
          <Header />
          {showSearchBar && <SearchBar label="Search for products"  />}
          {children}
          <Footer></Footer>
          </UserProvider>
          </AppProvider>
          </ProductProvider>
        </div>
      </body>
    </html>
  );
}
