import iconfiles from '../../assets/icons/index';
import PropTypes, { InferProps } from 'prop-types';

type IconName = keyof typeof iconfiles;

interface IconProps {
  name?: IconName;
  width?: string;
  height?: string;
  fill?: string;
}

const Icon: React.FC<IconProps> = ({
  name = 'arrowUp',
  width = '24',
  height = '24',
  fill = 'black',
}) => {
  const IconComponent = iconfiles[name];

  return (
    <div style={{ width: `${width}px`, height: `${height}px` }}>
      <IconComponent width={width} height={height} fill={fill} />
    </div>
  );
};

Icon.propTypes = {
  name: PropTypes.oneOf(
    Object.values(iconfiles),
  ) as InferProps<IconProps>['name'],
  width: PropTypes.string,
  height: PropTypes.string,
  fill: PropTypes.string,
};

export default Icon;
