import iconfiles from '../../assets/icons/index';

type IconName = keyof typeof iconfiles;

interface IconProps {
  name: IconName;
  width?: string;
  height?: string;
  fill?: string;
  onClick?: () => void;
}

const Icon = ({
  name = 'arrowUp',
  width = '24',
  height = '24',
  fill = 'black',
  onClick,
}: IconProps) => {
  const IconComponent = iconfiles[name];

  return (
    <IconComponent
      width={width}
      height={height}
      fill={fill}
      onClick={onClick}
    />
  );
};

export default Icon;
