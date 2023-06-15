const elapsedTime = (createdAt: string) => {
  const minute = 60 * 1000;
  const hour = 60 * minute;
  const day = 24 * hour;
  const week = 7 * day;
  const month = 4 * week;
  const year = 12 * month;

  const currentDate = new Date();
  const createdDate = new Date(createdAt);
  const elapsedTime = currentDate.getTime() - createdDate.getTime();
  let result = '';

  if (elapsedTime < week) {
    if (elapsedTime < hour) {
      const minutes = Math.floor(elapsedTime / minute);
      result = minutes + '분 전';
    } else if (elapsedTime < day) {
      const hours = Math.floor(elapsedTime / hour);
      result = hours + '시간 전';
    } else {
      const days = Math.floor(elapsedTime / day);
      result = days + '일 전';
    }
  } else if (elapsedTime < month) {
    const weeks = Math.floor(elapsedTime / week);
    result = weeks + '주 전';
  } else if (elapsedTime < year) {
    const months = Math.floor(elapsedTime / month);
    result = months + '개월 전';
  } else {
    const years = Math.floor(elapsedTime / year);
    const remainingMonths = Math.floor((elapsedTime % year) / month);

    if (remainingMonths === 0) {
      result = years + '년 전';
    } else {
      result = years + '년 ' + remainingMonths + '개월 전';
    }
  }

  return result;
};

export default elapsedTime;
