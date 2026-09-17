import React, { useEffect } from "react";
import MenuIcon from "@mui/icons-material/Menu";
import { Badge, Drawer, IconButton } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { NotificationsActive } from "@mui/icons-material";
import { useDispatch, useSelector } from "react-redux";
import { fetchNotificationsByBusiness } from "../../../Redux/Notifications/action";
import useNotificationWebsoket from "../../../util/useNotificationWebsoket";

const Navbar = ({ DrawerList }) => {
  const navigate = useNavigate();
  const [open, setOpen] = React.useState(false);
  const { notification, business } = useSelector((store) => store);
  const dispatch = useDispatch();

  const toggleDrawer = (newOpen) => () => {
    setOpen(newOpen);
  };

  useEffect(() => {
    if (business.business?.id) {
      dispatch(
        fetchNotificationsByBusiness({
          businessId: business.business.id,
          jwt: localStorage.getItem("jwt"),
        })
      );
    }
  }, [business.business?.id]);

  useNotificationWebsoket(business.business?.id, "business");

  return (
    <div className="h-[10vh] flex items-center justify-between px-5 border-b">
      <div className="flex items-center gap-3 ">
        <IconButton onClick={toggleDrawer(true)} color="primary">
          <MenuIcon color="primary" />
        </IconButton>

        <h1
          onClick={() => navigate("/")}
          className="logo text-xl cursor-pointer"
        >
          Business Booking
        </h1>
      </div>

      <IconButton onClick={() => navigate("/business-dashboard/notifications")}>
        <Badge
          badgeContent={notification.notifications.length}
          color="secondary"
        >
          <NotificationsActive color="primary" />
        </Badge>
      </IconButton>

      <Drawer open={open} onClose={toggleDrawer(false)}>
        <DrawerList toggleDrawer={toggleDrawer} />
      </Drawer>
    </div>
  );
};

export default Navbar;
