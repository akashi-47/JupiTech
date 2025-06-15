// components/helpersComponents/Card.tsx
import Image from "next/image";
import Link from "next/link";
import { Product } from "@/types/types";

export default function Card({ product }: { product: Product }) {
  return (
    <Link href={`/products/${product.id}`} passHref>
      <div
        key={product.id}
        className="bg-gray-800 p-6 rounded-lg shadow-lg cursor-pointer transform transition-all duration-300 hover:scale-105 hover:shadow-2xl "
        style={{ height: "30rem" }}
      >
        <Image
          src={"/images/"+product.imageURL}
          alt={product.title}
          width={300}
          height={200}
          className="rounded-lg mb-4"
        />
        <h3 className="text-xl font-semibold text-indigo-300">{product.title}</h3>
        <p className="mt-2 text-gray-400">{product.description}</p>
        <p className="mt-4 text-lg font-bold text-white">{product.title}</p>
      </div>
    </Link>
  );
}
