"use client";


import { BsCartXFill } from "react-icons/bs";

import Image from "next/image";
import { useProductContext } from "@/context/productContext";
import { useRouter } from "next/navigation";
import { useUserContext } from "@/context/userContext";


export default function GetCart() {

    const {productList, total, updateQuantity,removeProduct} = useProductContext();
    const router = useRouter();
    const {isLoggedIn} = useUserContext(); // Accessing user context
    const toCheckout = () => {
      if (isLoggedIn) 
        router.push("/checkout");
      else 
        router.push("/login");
    }



  // const [quantity, setQuantity] = useState(1);
  // const products = [
  //   {
  //     id: 1,
  //     name: "iPhone 14",
  //     description: "Latest iPhone model with advanced features.",
  //     price: "$999",
  //     image: smartphoneImg,
  //     type: "smartphone",
  //   },
  //   {
  //     id: 2,
  //     name: "Samsung Galaxy S22",
  //     description: "Smartphone with high-end performance.",
  //     price: "$899",
  //     image: smartphoneImg,
  //     type: "smartphone",
  //   },
  // ];

  return (
    <div className="mx-auto max-w-6xl px-4 sm:px-6 w-4/5 my-9" style={{ minHeight: "38rem" }}>
      <p className="text-2xl font-bold mb-6">Shopping Cart</p>
      {productList.length == 0 ? (
        <div className="flex items-center justify-center text-center my-12">
          <span className="text-6xl "><BsCartXFill /></span>
          <div className="ml-4">
            <h2 className="text-xl ">
              Your cart is empty
              
            </h2>
          </div>
        </div>
      ) : (
        <div className="grid grid-cols-1 lg:grid-cols-[66.56%,30%] gap-8">
          <div className="space-y-6">
            {productList.map((product) => (
              <div
                key={product.id}
                className="flex items-center justify-between p-6 border-[1px] border-[#9932CC] bg-transparent rounded-lg"
              >
                <div className="flex items-center">
                  <Image src={product.imageURL} alt={product.title} className="w-24 h-24 object-cover rounded-md" />
                  <div className="ml-4">
                    <h3 className="text-xl font-semibold ">{product.title}</h3>
                    <p className="">{product.description}</p>
                    <p className="text-lg font-bold">{product.prix}</p>
                    <div className="quantity">
          <span>Quantity: </span>
          <select 
  className="block"
  value={product.quantite} 
  onChange={(e) => updateQuantity(product, Number(e.target.value))} // Update quantity in context
>
  {Array.from({ length: 30 }, (_, i) => (
    <option key={i + 1} value={i + 1}>
     
       {i + 1}
    </option>
  ))}
</select>
</div>
                  </div>
                </div>
                <button onClick={()=>removeProduct(product.id)} className="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-opacity-50">
                  Remove
                </button>
              </div>
            ))}
          </div>
          <div className="border-[1px] border-[#9932CC] shadow-lg rounded-lg p-6">
            <h2 className="text-xl font-semibold  mb-4">Order Summary</h2>
            <div className="space-y-4">
              <div className="flex justify-between text-lg ">
                <span>Total</span>
                <span className="font-semibold ">{total}</span>
              </div>
              <button onClick={toCheckout} className="w-full bg-green-500 text-white px-6 py-3 rounded-md text-lg hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-500 focus:ring-opacity-50">
                Proceed to Checkout
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
