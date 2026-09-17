import React, { useEffect } from "react";
import BusinessCard from "./BusinessCard";
import { useDispatch, useSelector } from "react-redux";
import { fetchBusinesses } from "../../../Redux/Business/action";

const BusinessList = ({businesses}) => {

  return (
    <div className="flex gap-6 flex-wrap ">
      {businesses?.map((item) => (
        <BusinessCard key={item.id} business={item}/>
      ))}
    </div>
  );
};

export default BusinessList;
