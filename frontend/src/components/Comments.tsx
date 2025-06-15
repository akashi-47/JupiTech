"use client";

import Image from "next/image";

// Testimonial images
import TestimonialImg01 from "@/public/images/testimonial-01.jpg";
import TestimonialImg02 from "@/public/images/testimonial-02.jpg";
import TestimonialImg03 from "@/public/images/testimonial-03.jpg";
import TestimonialImg04 from "@/public/images/testimonial-04.jpg";
import TestimonialImg05 from "@/public/images/testimonial-05.jpg";
import TestimonialImg06 from "@/public/images/testimonial-06.jpg";
import TestimonialImg07 from "@/public/images/testimonial-07.jpg";
import TestimonialImg08 from "@/public/images/testimonial-08.jpg";

const testimonials = [
  {
    name: "John D.",
    content:
      "I recently bought a smartphone from this store, and I'm extremely happy with my purchase. The product quality is top-notch, and the delivery was super fast. Highly recommended!",
    clientImage: TestimonialImg01,
  },
  {
    name: "Sarah K.",
    content:
      "I purchased a laptop from this store for work, and it has been amazing! It's fast, efficient, and has all the features I need. I will definitely buy again in the future.",
    clientImage: TestimonialImg02,
  },
  {
    name: "Michael L.",
    content:
      "Great experience! The sound system I bought was exactly what I was looking for. The setup was easy, and the sound quality is incredible. Very satisfied with my purchase!",
    clientImage: TestimonialImg03,
  },
  {
    name: "Emily T.",
    content:
      "I bought a smart home security camera, and it works perfectly. Setting it up was a breeze, and it gives me peace of mind knowing my home is secure. Excellent product!",
    clientImage: TestimonialImg04,
  },
  {
    name: "David P.",
    content:
      "I ordered a gaming laptop, and it's amazing! It runs every game I throw at it without any issues. Plus, the customer service was fantastic and very helpful.",
    clientImage: TestimonialImg05,
  },
  {
    name: "Sophia G.",
    content:
      "I bought a smartwatch, and I am loving it! It's so comfortable, and the features are just perfect for tracking my fitness goals. Great value for money!",
    clientImage: TestimonialImg06,
  },
  {
    name: "Chris M.",
    content:
      "I purchased a tablet for my work and it exceeded my expectations. The battery life is excellent, and it's incredibly responsive. This store offers amazing deals.",
    clientImage: TestimonialImg07,
  },
  {
    name: "Olivia W.",
    content:
      "The wireless headphones I bought from this store are the best I've ever had. The sound quality is crystal clear, and they are super comfortable to wear for hours.",
    clientImage: TestimonialImg08,
  },
];

export default function Testimonials() {


  return (
    <div className="mx-auto max-w-6xl px-4 sm:px-6">
      <div className="border-t py-12 [border-image:linear-gradient(to_right,transparent,--theme(--color-slate-400/.25),transparent)1] md:py-20">
        {/* Section header */}
        <div className="mx-auto max-w-3xl pb-12 text-center">
          <h2 className="animate-[gradient_6s_linear_infinite] bg-[linear-gradient(to_right,var(--color-gray-200),var(--color-indigo-200),var(--color-gray-50),var(--color-indigo-300),var(--color-gray-200))] bg-[length:200%_auto] bg-clip-text pb-4 font-nacelle text-3xl font-semibold text-transparent md:text-4xl">
            Do not take our word for it
          </h2>
          <p className="text-2xl text-orange-400">
            Here is what some of our customers have to say about their recent
            purchases.
          </p>
        </div>

        <div>
          {/* Cards */}
          <div className="mx-auto grid max-w-sm items-start gap-6 sm:max-w-none sm:grid-cols-2 lg:grid-cols-3">
            {testimonials.map((testimonial, index) => (
              <div key={index} className="group">
                <article className="relative rounded-2xl bg-linear-to-br from-gray-900/50 via-gray-800/25 to-gray-900/50 p-5 backdrop-blur-xs transition-opacity">
                  <div className="flex flex-col gap-4">
                    <p className="text-indigo-200/65 before:content-['“'] after:content-['”']">
                      {testimonial.content}
                    </p>
                    <div className="flex items-center gap-3">
                      <div className="flex-shrink-0">
                        <Image
                          src={testimonial.clientImage}
                          alt={`${testimonial.name} photo`}
                          width={36}
                          height={36}
                          className="rounded-full"
                        />
                      </div>
                      <div className="text-sm font-medium text-gray-200">
                        <span>{testimonial.name}</span>
                      </div>
                    </div>
                  </div>
                </article>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
