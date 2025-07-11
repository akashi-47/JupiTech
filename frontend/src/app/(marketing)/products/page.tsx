"use client";
import { useState, useEffect } from "react";
import { useSearchParams } from "next/navigation";

import Card from "@/components/helpersComponents/Card";
import { useAppContext } from "@/context/appContext";


export default function Products() {
  const { products, loading } = useAppContext();
  const [category, setCategory] = useState<string>("SMARTPHONE");
  const [filteredProducts, setFilteredProducts] = useState(products);
  const searchParams = useSearchParams();
  const search = searchParams.get("search") || "";

  // Update filtered products whenever category or products change
  useEffect(() => {
   if(products.length>0)
    {
    let filtered = products.filter((product) => product.categorie === category);
      if (search.trim()) {
        filtered = filtered.filter(
          (product) =>
            product.title.toLowerCase().includes(search.toLowerCase()) ||
            product.description.toLowerCase().includes(search.toLowerCase())
        );
      }
      setFilteredProducts(filtered);
    }
  }, [category, products, search]);

  return (
    <div className="mx-auto max-w-6xl px-4 sm:px-6">
      {/* Section header */}
      <div className="text-center py-12">
        <h1 className="text-4xl font-semibold text-indigo-400">Explore Our Products</h1>
        <p className="mt-4 text-lg text-gray-400">
          Browse through our wide selection of products. Choose a category to get started.
        </p>
      </div>

      {/* Category buttons */}
      <div className="flex justify-center gap-6 mb-12">
        <button
          className={`text-xl font-semibold ${category === "SMARTPHONE" ? "text-indigo-500" : "text-gray-600"}`}
          onClick={() => setCategory("SMARTPHONE")}
        >
          SMARTPHONEs
        </button>
        <button
          className={`text-xl font-semibold ${category === "PC" ? "text-indigo-500" : "text-gray-600"}`}
          onClick={() => setCategory("PC")}
        >
          PCs
        </button>
        <button
          className={`text-xl font-semibold ${category === "ACCESSOIRE" ? "text-indigo-500" : "text-gray-600"}`}
          onClick={() => setCategory("ACCESSOIRE")}
        >
          ACCESSOIREs
        </button>
      </div>

      {/* Product grid */}
      {loading ? (
        <div className="text-center text-xl text-gray-500">Loading products...</div>
      ) : filteredProducts.length > 0 ? (
        <div className="grid gap-8 sm:grid-cols-2 lg:grid-cols-3">
          {filteredProducts.map((product) => (
            <Card
              key={product.id}
              product={product}
            />
          ))}
        </div>
      ) : (
        <div className="text-center text-xl text-gray-500">No products found in this category</div>
      )}
    </div>
  );
}