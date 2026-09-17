import { ThemeProvider } from "@mui/material";

import greenTheme from "./Theme/greenTheme";
import { Route, Routes, useNavigate } from "react-router-dom";

import BusinessDashboard from "./salon/pages/SellerDashboard/BusinessDashboard";
import Auth from "./Auth/Auth";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getUser } from "./Redux/Auth/action";
import BecomePartner from "./salon/pages/Become Partner/BecomePartnerForm";
import CustomerRoutes from "./routes/CustomerRoutes";
import AdminRoutes from "./routes/AdminRoutes";
import AdminDashboard from "./Admin/pages/Dashboard/Dashboard";
import redTheme from "./Theme/redTheme";


function App() {
  const { auth } = useSelector((store) => store);
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    dispatch(getUser(auth.jwt || localStorage.getItem("jwt")));
  }, [auth.jwt]);


  useEffect(()=>{
    if(auth.user?.role==="ROLE_SALON_OWNER"){
      navigate("/business-dashboard");
    }
  },[auth.user?.role])

  return (
    <ThemeProvider theme={greenTheme}>
      <div className="relative">

        
        <Routes>
          {<Route path="/business-dashboard/*" element={<BusinessDashboard />} />}
          <Route path="/login" element={<Auth />} />
          <Route path="/register" element={<Auth />} />
          <Route path="/become-partner" element={<BecomePartner />} />
          <Route path="/admin/*" element={<AdminDashboard/>} />
          <Route path="*" element={<CustomerRoutes />} />
        </Routes>
      </div>
    </ThemeProvider>
  );
}

export default App;
