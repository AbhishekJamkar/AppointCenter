import React, { useEffect, useState } from "react";
import ServiceCard from "./ServiceCard";
import {
  Box,
  Button,
  Divider,
  IconButton,
  Modal,
  Typography,
} from "@mui/material";

import { useDispatch, useSelector } from "react-redux";
import { fetchServicesByBusinessId } from "../../../../Redux/Business Services/action";
import { useParams } from "react-router-dom";
import { fetchBusinessById } from "../../../../Redux/Business/action";
import { getCategoriesByBusiness } from "../../../../Redux/Category/action";

import BusinessDetail from "./BusinessDetail";
import BusinessServiceDetails from "./BusinessServiceDetails";
import CreateReviewForm from "../Reviews/CreateReviewForm";
import Review from "../Reviews/Review";
import { fetchBookedSlots } from "../../../../Redux/Booking/action";

const tabs = [{ name: "All Services" }, { name: "Reviews" },{name:"Create Review"}];

const BusinessDetails = () => {
  const [activeTab, setActiveTab] = useState(tabs[0]);

  const dispatch = useDispatch();
  const { id } = useParams();

  React.useEffect(() => {
    dispatch(fetchBusinessById(id));
    dispatch(
      getCategoriesByBusiness({
        businessId: id,
        jwt: localStorage.getItem("jwt"),
      })
    );
  
  }, [id]);

  const handleActiveTab = (tab) => () => {
    setActiveTab(tab);
  };

  return (
    <div className="px-5 lg:px-20">
      <BusinessDetail />
      <div className="space-y-5">
        <div className="flex gap-2">
          {tabs.map((tab) => (
            <Button
            // color="secondary"
              onClick={handleActiveTab(tab)}
              variant={tab.name === activeTab?.name ? "contained" : "outlined"}
            >
              {tab.name}
            </Button>
          ))}
        </div>
        <Divider />
        
      </div>
      <div>
          {activeTab?.name==="Create Review"?
          <div className="flex justify-center ">
            <CreateReviewForm/>
          </div>:activeTab.name==="Reviews"?<div>
            <Review/>
          </div>:<BusinessServiceDetails/>}
        </div>
      
    </div>
  );
};

export default BusinessDetails;
