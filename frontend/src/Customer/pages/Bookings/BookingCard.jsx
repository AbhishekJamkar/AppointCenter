import React from "react";
import ArrowRightAltIcon from "@mui/icons-material/ArrowRightAlt";
import { Button } from "@mui/material";
import { useDispatch } from "react-redux";
import { updateBookingStatus } from "../../../Redux/Booking/action";

const BookingCard = ({ booking }) => {
  const dispatch = useDispatch();

  const handleCancelBooking = () => {
    // const status=booking.status==="CANCELLED"?"PENDING":"CANCELLED"
    dispatch(
      updateBookingStatus({
        bookingId: booking.id,
        status: "CANCELLED",
        jwt: localStorage.getItem("jwt"),
      })
    );
  };
  return (
    <div className="p-5 rounded-md bg-slate-100 md:flex items-center justify-between">
      <div className="space-y-2">
        <h1 className="text-2xl font-bold">{booking.business.name}</h1>
        <div>
          {booking.services.map((service) => (
            <li key={service.id}>{service.name}</li>
          ))}
        </div>
        <div>
          <p className="font-semibold">
            Time & Date <ArrowRightAltIcon /> {booking.startTime?.split("T")[0]}{" "}
          </p>
          <p className="text-slate-700">
            {booking.startTime?.split("T")[1]} To{" "}
            {booking.endTime?.split("T")[1]}
          </p>
        </div>
      </div>
      <div className="space-y-2">
        <img className="h-28 w-28" src={booking.business.images[0]} alt="" />
        <p className="text-center">₹{booking.totalPrice}</p>
        <Button
          onClick={handleCancelBooking}
          color="error"
          fullWidth
          variant={booking.status==="CANCELLED"?"contained":"outlined"}
        >
          {booking.status==="CANCELLED"?"CANCELLED":"Cancel"}
        </Button>
      </div>
    </div>
  );
};

export default BookingCard;
