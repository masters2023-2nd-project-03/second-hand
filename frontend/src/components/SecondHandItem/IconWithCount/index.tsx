import Icon from '../../Icon';
import iconfiles from '../../../assets/icons';
import * as S from './styles';

type IconName = keyof typeof iconfiles;

interface IconWithCountProps {
  name: IconName;
  count: number;
}

const IconWithCount = ({ name, count }: IconWithCountProps) => {
  return (
    <>
      {count > 0 && (
        <S.IconWithCountStyle>
          <Icon name={name} width={'20'} height={'16'} />
          <span>{count}</span>
        </S.IconWithCountStyle>
      )}
    </>
  );
};

export default IconWithCount;
