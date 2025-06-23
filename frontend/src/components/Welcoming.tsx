import Link from "next/link";


export default function Welcoming() {
  return (
    <section>
      <div className="mx-auto max-w-6xl px-4 sm:px-6">
       
        <div className="py-12 md:pt-20 md:pb-5">
          {/* Section header */}
          <div className="pb-12 text-center md:pb-20">
            <h1
              className="animate-[gradient_6s_linear_infinite] bg-[linear-gradient(to_right,var(--color-gray-200),var(--color-indigo-200),var(--color-gray-50),var(--color-indigo-300),var(--color-gray-200))] bg-[length:200%_auto] bg-clip-text pb-5 font-nacelle text-4xl font-semibold  md:text-5xl"
              data-aos="fade-up"
            >
             Welcome to Our Store
            </h1>
            <div className="mx-auto max-w-3xl">
              <p
                className="mb-8 text-xl text-indigo-200/65"
                data-aos="fade-up"
                data-aos-delay={200}
              >
                Our store offers a wide range of products to meet your needs. From
                electronics to clothing, we have something for everyone.
              </p>
              <div className="mx-auto max-w-xs sm:flex sm:max-w-none sm:justify-center">
  <div data-aos="fade-up" data-aos-delay={400}>
    <Link className="mb-4 w-full rounded bg-indigo-600 px-6 py-3 text-center font-semibold text-white transition-all hover:bg-indigo-700 sm:mb-0 sm:w-auto"
      href="/products">
      
    
      <span className="inline-flex items-center">
        Start Shopping
        <span className="ml-1 text-white/70 transition-transform group-hover:translate-x-1">
          →
        </span>
      </span>
      </Link>
    
  </div>
  <div data-aos="fade-up " data-aos-delay={600}>
    <Link
      className="w-full bg-orange-600 rounded border border-gray-600 px-6 py-3 text-center font-semibold text-gray-300 transition-all hover:bg-orange-700 sm:ml-4 sm:w-auto"
      href="/signup?role=SELLER"
    >
     Become a Seller
    </Link>
  </div>
</div>

            </div>
          </div>

         
        </div>
      </div>
    </section>
  );
}
