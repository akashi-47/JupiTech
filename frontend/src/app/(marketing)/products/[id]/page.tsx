
"use client";
import { useParams } from "next/navigation";
import { useState, useEffect } from "react";
import { useAppContext } from "@/context/appContext";
import { Product } from "@/types/types";
import Image from "next/image";
import { FaCartArrowDown } from "react-icons/fa";
import SimiliarItems from "../../../../components/SimiliarItems";
import ratingStarts from "@/public/images/rating_starts.png";



export default function ProductPage() {
  const { id } = useParams();
  const idNumber = parseInt(id as string, 10); // Convert the id to a number
  const [product, setProduct] = useState<Product | null>(null);
  const { getProduct } = useAppContext();
  const [quantity, setQuantity] = useState(1);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const productData = await getProduct(idNumber);
        if (productData !== undefined) {
          setProduct(productData); // Product found
        } else {
          setProduct(null); // Product not found
        }
      } catch (error) {
        console.error("Error fetching product:", error);
        setProduct(null); // Fallback in case of error
      }
    };

    fetchProduct();
  }, [idNumber, getProduct]);

  // Render error page or 404-like message if no product
  if (product === null) {
    return (
      <div className="min-h-screen flex items-center justify-center">
        <h1 className="text-3xl text-red-500">Product not found!</h1>
      </div>
    );
  }

  const addToCart = async () => {
    const productToAdd = { ...product, quantite: quantity };
    // Add to cart logic here
    console.log("Product added to cart:", productToAdd);
  };

  return (
    <div className="min-h-screen bg-gray-950 text-white px-4">
      <div className="flex justify-center items-center product-section md:flex-row flex-col xl:px-14 xl:mx-10">
        <div className="w-96">
        <Image
  src={`/images/${product.imageURL}`} // Add leading slash here
  alt={product.title}
  className="rounded-xl shadow-2xl transition-transform duration-300 hover:scale-105"
  width={400}
  height={400}
/>

        
        </div>

        <div className="flex-1 space-y-6">
          <h1 className="text-4xl font-bold text-indigo-400">{product.title}</h1>
          <span className="inline-block px-4 py-1 bg-indigo-700/40 text-indigo-100 rounded-full text-sm uppercase tracking-widest">
            {product.categorie}
          </span>
          <p className="text-gray-300 text-lg leading-relaxed">{product.description}</p>
          <div className="relative">
            4.5
            <Image
              src={ratingStarts}
              alt={"rating"}
              className="rounded-xl shadow-2xl transition-transform duration-300 hover:scale-105 rate_image"
              placeholder="blur"
            />
          </div>
          <p style={{ color: "limegreen", fontSize: "larger" }}>In Stock</p>
          <div className="text-2xl font-semibold ">{product.prix}</div>
          <div className="quantity">
            <span>Quantity: </span>
            <select
              className="block"
              value={quantity}
              onChange={(e) => setQuantity(Number(e.target.value))}
            >
              {Array.from({ length: 30 }, (_, i) => (
                <option key={i + 1} value={i + 1}>
                  {i + 1}
                </option>
              ))}
            </select>
          </div>
          <button
            onClick={addToCart}
            className="mt-4 inline-flex justify-center items-center gap-2 w-36 rounded-md bg-indigo-600 px-5 py-2.5 text-white font-medium transition hover:bg-indigo-700"
          >
            Add to Cart
            <span className="text-white/70">+</span>
          </button>
          <button className="mt-4 inline-flex justify-center items-center gap-2 w-36 rounded-md bg-orange-500 px-5 py-2.5 text-white font-medium transition hover:bg-orange-600 ml-2">
            Buy now <FaCartArrowDown />
          </button>
        </div>
      </div>
      <hr className="mx-auto text-gray-400 w-3/4" />
      <SimiliarItems />
    </div>
  );
}
