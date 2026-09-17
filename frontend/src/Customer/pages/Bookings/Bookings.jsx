import React, { useEffect } from "react";
import BookingCard from "./BookingCard";
import { useDispatch, useSelector } from "react-redux";
import { fetchCustomerBookings } from "../../../Redux/Booking/action";

const Bookings = () => {
  const dispatch = useDispatch();
  const { booking } = useSelector((store) => store);

  useEffect(() => {
    dispatch(fetchCustomerBookings(localStorage.getItem("jwt")));
  }, []);

  return (
    <div className="px-5 md:flex flex-col items-center mt-10 min-h-screen">
      <div>
        <h1 className="text-3xl font-bold py-5">My Bookings</h1>
      </div>
      <div className="space-y-4 md:w-[35rem]">
        {booking.bookings.map((item) => (
          <BookingCard booking={item} />
        ))}
      </div>
    </div>
  );
};

export default Bookings;
