import SmallItem from "./helpersComponents/SmallItem";
import { useAppContext } from "@/context/appContext"; // Adjust if you use useProductContext

export default function SimilarItems() {
  const { products } = useAppContext(); // Or useProductContext if that's your cart context

  // For demo: show the first 4 products as "similar"
  const similarItems = products.slice(0, 4);

  return (
    <div className="mt-8 flex justify-center items-center flex-col">
      <h3 className="text-xl font-semibold text-blue-800">Similar Items</h3>
      <div className="flex gap-4 mt-6 ">
        {similarItems.map((item) => (
          <SmallItem key={item.id} item={item} />
        ))}
      </div>
    </div>
  );
}
