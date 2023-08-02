const currentDate = new Date();
const hours = currentDate.getHours();
const minutes = currentDate.getMinutes();

let formattedHours;

if (hours >= 12) {
  formattedHours = hours === 12 ? 12 : hours - 12;
} else {
  formattedHours = hours === 0 ? 12 : '0' + hours;
}

const formattedMinutes = minutes < 10 ? '0' + minutes : minutes;

const currentTime = `${formattedHours}:${formattedMinutes}`;

export default currentTime;
