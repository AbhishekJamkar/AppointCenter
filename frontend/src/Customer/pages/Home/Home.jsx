import React, { useEffect } from "react";
import { services } from "../../../Data/Services";
import HomeServiceCard from "./HomeServiceCard";
import BusinessList from "../Salon/BusinessList";
import Banner from "./Banner";
import { useDispatch, useSelector } from "react-redux";
import { fetchBusinesses } from "../../../Redux/Business/action";

const Home = () => {
  const { business } = useSelector((store) => store);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchBusinesses());
  }, []);
  return (
    <div className="space-y-20 ">
      <section>
        <Banner />
      </section>
      <section className="space-y-10 lg:space-y-0 lg:flex items-center gap-5 px-20">
        <div className="w-full lg:w-1/2 ">
          <h1 className="text-2xl font-semibold pb-9">
            What are you looking for today?👀
          </h1>
          <div className="flex flex-wrap justify-center items-center gap-5">
            {services.map((item) => (
              <HomeServiceCard key={item.id} item={item} />
            ))}
          </div>
        </div>
        <div className="w-full lg:w-1/2 border grid gap-3 grid-cols-2 grid-rows-12 h-[45vh] md:h-[90vh] ">
          <div className="row-span-7">
            <img
              className="h-full w-full rounded-md"
              src="https://tinw.in//uploads/vcards/services/31044/0bf503887a2cbdc7e903906b134f8282.jpg"
              alt=""
            />
          </div>
          <div className="row-span-5">
            <img
              className="h-full w-full rounded-md"
              src="https://eaudit.ge/uploads/posts/2023-06/legal.jpg"
              alt=""
            />
          </div>
          <div className="row-span-7">
            <img
              className="h-full w-full rounded-md"
              src="https://www.advamed.org/wp-content/uploads/2023/06/doctor-consultation-patient-smiling.jpg"
              alt=""
            />
          </div>
          <div className="row-span-5">
            <img
              className="h-full w-full rounded-md"
              src="https://cpimg.tistatic.com/13121898/b/4/Professional-Hair-Cutting-Cape-for-Salon-Home-Use-1-Pc-.jpeg"
              alt=""
            />
          </div>
        </div>
      </section>
      <section className="px-20">
        <h1 className="text-3xl font-bold pb-10 ">Book Your Favorite Appointment</h1>
        <BusinessList businesses={business.businesses} />
      </section>
    </div>
  );
};

export default Home;
