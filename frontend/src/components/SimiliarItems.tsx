import phone from "@/public/images/phone.png"; // Using one of your existing images
import pc from "@/public/images/pc.png";
import headphone from "@/public/images/headphone.png";
import SmallItem from "./helpersComponents/SmallItem";

const mockItems = [
  {
    id: 1,
    title: "Smartphone",
    price: "$699.99",
    image: phone, // This is now correctly typed as StaticImageData
  },
  {
    id: 2,
    title: "Laptop",
    price: "$999.99",
    image: pc,
  },
  {
    id: 3,
    title: "Headphone",
    price: "$199.99",
    image: headphone,
  },
  {
    id: 4,
    title: "Smartwatch",
    price: "$299.99",
    image: phone, // You can replace this with any other image
  },
];

export default function SimilarItems() {
  return (
    <div className="mt-8 flex justify-center items-center flex-col">
      <h3 className="text-xl font-semibold text-blue-800">Similar Items</h3>
      <div className="flex gap-4 mt-6 ">
        {mockItems.map((item) => (
          <SmallItem key={item.id} item={item} />
        ))}
      </div>
    </div>
  );
}
