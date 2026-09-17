import React, { useEffect, useState } from "react";
import BusinessList from "./BusinessList";
import { useDispatch, useSelector } from "react-redux";
import { searchBusiness } from "../../../Redux/Business/action";
import { esES } from "@mui/x-date-pickers/locales";

const SearchBusiness = () => {
  const { business } = useSelector(store=>store);
  const dispatch = useDispatch();

  const handleSearch = (e) => {
    console.log("handleSearch ", e.target.value);
    dispatch(
      searchBusiness({ jwt: localStorage.getItem("jwt"), city: e.target.value })
    );
  };
  return (
    <div className="lg:px-20 px-5">
      <div className="flex flex-col justify-center items-center pb-10 text-white z-20 space-y-3 px-5">
        <input
          onChange={handleSearch}
          className="cursor-pointer border bg-white rounded-md py-4 w-[15rem] md:w-[33rem] outline-none text-black px-5"
          placeholder="search business by city..."
        />
      </div>
      <div>
        <BusinessList businesses={business.searchBusinesses} />
      </div>
    </div>
  );
};

export default SearchBusiness;
