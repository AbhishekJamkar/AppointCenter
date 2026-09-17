import React, { useEffect } from "react";


import Navbar from "../../../admin seller/components/navbar/Navbar";
import BusinessRoutes from "../../../routes/BusinessRoutes";
import { useDispatch } from "react-redux";
import { fetchBusinessByOwner } from "../../../Redux/Business/action";
import BusinessDrawerList from "../../components/SideBar/DrawerList";
import { getBusinessReport } from "../../../Redux/Booking/action";

const BusinessDashboard = () => {
  const dispatch=useDispatch();
  // const {}
  useEffect(() => {
    dispatch(fetchBusinessByOwner(localStorage.getItem("jwt")));
    dispatch(getBusinessReport(localStorage.getItem("jwt")))
  }, []);


  return (
    <div className="min-h-screen">
      <Navbar DrawerList={BusinessDrawerList}/>
      <section className="lg:flex lg:h-[90vh]">
        <div className="hidden lg:block h-full">
        <BusinessDrawerList/>
        </div>
        <div className="p-10 w-full lg:w-[80%]  overflow-y-auto">
          <BusinessRoutes />
        </div>
      </section>
    </div>
  );
};

export default BusinessDashboard;
