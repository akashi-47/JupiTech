import Image from "next/image";
import Link from "next/link";
import { Product } from "@/types/types";

export default function SmallItem({
  item,
}: {
  item: Product;
}) {
  return (
    <Link
      href={`/products/${item.id}`}
      className="flex-none w-48 p-4 bg-gray-800 shadow-md rounded-md transform transition-all duration-300 hover:scale-105 hover:shadow-2xl"
    >
      <Image
        className="w-full h-32 object-cover rounded-md"
        src={"/images/" + item.imageURL}
        width={200}
        height={150}
        alt={item.title}
      />
      <h4 className="mt-2 text-sm font-medium ">{item.title}</h4>
      <p className="text-xs mt-1">{item.prix} DA</p>
    </Link>
  );
}
