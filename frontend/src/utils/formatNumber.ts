export const formatNumber = (value: number) => {
  return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};

export const formatString = (value: string) => {
  return value.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};
