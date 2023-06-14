import Button from '../Button';
import Icon from '../Icon';
import elapsedTime from '../../utils/elapsedTime';

interface SecondHandItemProps {
  title: string;
  createdAt: string;
  status: string;
  price: number;
  location: string;
  chatCount: number;
  interestCount: number;
  imageURI: string;
  option?: boolean;
}

const SecondHandItem = ({
  title,
  createdAt,
  status,
  price,
  location,
  chatCount,
  interestCount,
  imageURI,
  option = false,
}: SecondHandItemProps) => {
  return (
    <div className="ItemContainer">
      <div
        className="ItemImage"
        style={{
          width: '120px',
          height: '120px',
          backgroundImage: `url(${imageURI})`,
          backgroundSize: 'contain',
        }}
      />
      <div className="ItemInfo">
        <div className="ItemContents">
          <div className="Content">
            <div className="ColumnTop">{title}</div>
            <div className="ColumnMiddle">
              {location} ・ {elapsedTime(createdAt)}
            </div>
            <div className="ColumnBottom">
              <Button round={true}>{status}</Button>
              <span>{price}원</span>
            </div>
          </div>
          <div className="Option">
            {option && <Icon name={'ellipsis'} width={'17'} height={'20'} />}
          </div>
        </div>
        <div className="ItemIssue">
          {chatCount}
          {interestCount}
        </div>
      </div>
    </div>
  );
};

export default SecondHandItem;
