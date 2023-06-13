interface SecondHandItemProps {
  id: number;
  title: string;
  createdAt: string;
  status: string;
  price: number;
  location: string;
  chatCount: number;
  interestCount: number;
  imageURI: string;
}

const SecondHandItem = ({
  id,
  title,
  createdAt,
  status,
  price,
  location,
  chatCount,
  interestCount,
  imageURI,
}: SecondHandItemProps) => {
  return (
    <div>
      {id}, {title}, {createdAt}, {status}, {price}, {location},{chatCount},{' '}
      {interestCount},{imageURI}
    </div>
  );
};

export default SecondHandItem;
