import { Card } from "@mui/material";
import React from "react";
import { useDispatch } from "react-redux";
import { markNotificationAsRead } from "../../../Redux/Notifications/action";
import { useNavigate } from "react-router-dom";

const NotificationCard = ({ item,type }) => {
  const dispatch = useDispatch();
  const navigate=useNavigate();

  const handleReadNotification = () => {
    if(type==="USER"){
        dispatch(markNotificationAsRead({
          notificationId:item.id,
          jwt:localStorage.getItem("jwt")}));
        navigate("/bookings")
    }
    
  };
  return (
    <Card
      onClick={handleReadNotification}
      sx={{ bgcolor: (item.isRead && type==="USER") ? "white": "#EAF0F1"  }}
      className={`cursor-pointer p-5 flex items-center gap-5 
      }`}
    >
      🛎️
      <div>
        <p>{item.description}</p>
        <h1 className="space-x-3">
          {" "}
          {item.booking?.services?.map((service) => (
            <span key={service.id}>{service.name}</span>
          ))}
        </h1>
      </div>
    </Card>
  );
};

export default NotificationCard;
