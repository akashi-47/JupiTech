import Image from "next/image";
import phone from "@/public/images/phone.png";
import pc from "@/public/images/pc.png";
import headphone from "@/public/images/headphone.png";

export default function Features() {
  return (
    <section className="relative bg-gray-950 pb-10 text-gray-200">
   
      <div
  className="mb-12 flex flex-wrap justify-center gap-24"
  data-aos="fade-up"
>
  <Image
    className="rounded-lg shadow-lg"
    src={headphone}
    width={200}
    height={150}
    alt="Shopping for electronics"
  />
  <Image
    className="rounded-lg shadow-lg"
    src={pc}
    width={200}
    height={150}
    alt="Shopping for electronics"
  />
  <Image
    className="rounded-lg shadow-lg"
    src={phone}
    width={200}
    height={150}
    alt="Shopping for electronics"
  />
</div>

      <div className="mx-auto max-w-6xl px-4 sm:px-6">
        {/* Section header */}
        <div className="mx-auto max-w-3xl pb-12 text-center">
          <h2 className="text-3xl font-bold text-indigo-400 md:text-4xl">
            Shop the Latest Electronics
          </h2>
          <p className="mt-4 text-lg text-gray-400">
            Discover a wide selection of electronic gadgets, from smartphones and laptops to home automation and more—all in one place.
          </p>
        </div>

        {/* Thematic image */}
      
        {/* Features grid */}
        <div className="grid gap-8 sm:grid-cols-2 lg:grid-cols-3">
          <article className="rounded-lg bg-gray-800 p-6 shadow-md hover:shadow-lg transition">
            <h3 className="mb-2 text-xl font-semibold text-indigo-300">Smartphones</h3>
            <p className="text-gray-400">Stay updated with the newest models from top brands including Apple, Samsung, and Google.</p>
          </article>

          <article className="rounded-lg bg-gray-800 p-6 shadow-md hover:shadow-lg transition">
            <h3 className="mb-2 text-xl font-semibold text-indigo-300">Laptops</h3>
            <p className="text-gray-400">Browse high-performance laptops for work, gaming, and everything in between.</p>
          </article>

          <article className="rounded-lg bg-gray-800 p-6 shadow-md hover:shadow-lg transition">
            <h3 className="mb-2 text-xl font-semibold text-indigo-300">Smart Home</h3>
            <p className="text-gray-400">Control your environment with smart lights, security systems, and voice assistants.</p>
          </article>
        </div>
      </div>
    </section>
  );
}
