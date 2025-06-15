export const metadata = {
  title: "Home - Open PRO",
  description: "Page description",
};

import PageIllustration from "@/components/page-illustration";
import Welcoming from "@/components/Welcoming";

import Offers from "@/components/Offers";
import Comments from "@/components/Comments";



export default function Home() {
  return (
    <>
  
      <PageIllustration />
      <Welcoming />
      <Offers />
      <Comments />
      
    </>
  );
}
